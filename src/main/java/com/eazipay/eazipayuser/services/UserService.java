package com.eazipay.eazipayuser.services;

import com.eazipay.eazipayuser.dtos.LoginDTO;
import com.eazipay.eazipayuser.dtos.AuthPayload;
import com.eazipay.eazipayuser.dtos.RegisterDTO;
import com.eazipay.eazipayuser.dtos.RegisterResponse;
import com.eazipay.eazipayuser.entities.User;
import com.eazipay.eazipayuser.repository.UserRepository;
import com.eazipay.eazipayuser.security.JWTService;
import com.eazipay.eazipayuser.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public GenericResponse createUser(RegisterDTO input) {
       log.info("Registering the user");
        Optional<User> existingUser = userRepository.findByEmail(input.getEmail());
        if (existingUser.isPresent()) {
            return GenericResponse.builder()
                    .message("Email already exists")
                    .status("01").build();
        }

     log.info("creating the user entity");
        User newUser = User.builder()
                .username(input.getUsername())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .phoneNumber(input.getPhoneNumber())
                .build();


        User savedUser = userRepository.save(newUser);
        log.info("Saved the user to the database");

        RegisterResponse response = RegisterResponse.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
        log.info("Returning the response");

        return GenericResponse.builder()
                .status("00")
                .message("Registered Successfully")
                .data(response)
                .build();
    }

    public GenericResponse loginUser(LoginDTO request) {
        log.info("Request to login");

        // Find the user by email
        Optional<User>optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()){
            return GenericResponse.builder()
                    .message("User not found")
                    .status("01")
                    .build();
        }
        User user = optionalUser.get();


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return GenericResponse.builder()
                    .message("Invalid email or password")
                    .status("01")
                    .build();
        }


        String tokenGenerated = jwtService.generateToken(user.getEmail());

        AuthPayload response = AuthPayload.builder()
                .token(tokenGenerated)
                .email(user.getEmail())
                .username(user.getUsername())
                .build();

        return GenericResponse.builder()
                .status("00")
                .message("Logged in Successfully")
                .data(response)
                .build();
    }
}
