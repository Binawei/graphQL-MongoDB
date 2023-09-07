package com.eazipay.eazipayuser.resolver;

import com.eazipay.eazipayuser.dtos.LoginDTO;
import com.eazipay.eazipayuser.dtos.AuthPayload;
import com.eazipay.eazipayuser.dtos.RegisterDTO;
import com.eazipay.eazipayuser.dtos.RegisterResponse;
import com.eazipay.eazipayuser.entities.User;
import com.eazipay.eazipayuser.exceptions.UserNotFoundException;
import com.eazipay.eazipayuser.services.UserService;
import com.eazipay.eazipayuser.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserResolver {

    private final UserService userService;
    @MutationMapping
    public User createUser(@Argument RegisterDTO input) {
        GenericResponse response = userService.createUser(input);

        if ("00".equals(response.getStatus())) {
            log.info("User creation was successful, return the created user");
            RegisterResponse registerResponse = (RegisterResponse) response.getData();


            User user = User.builder()
                    .username(registerResponse.getUsername())
                    .email(registerResponse.getEmail())
                    .phoneNumber(input.getPhoneNumber())
                    .build();
            log.info("Registration is successful");
            return user;
        } else {
            log.info("Registration has failed");
            return null;
        }
    }

    @QueryMapping
    public AuthPayload loginUser(@Argument LoginDTO request) {
        log.info("Received login request with email: {}", request.getEmail());

        try {
            GenericResponse genericResponse = userService.loginUser(request);

            if (genericResponse.getStatus().equals("00")) {
                AuthPayload response = (AuthPayload) genericResponse.getData();
                log.info("User logged in successfully with email: {}", request.getEmail());
                return response;
            } else {
                log.error("Failed to log in with email: {}", request.getEmail());
                throw new UserNotFoundException("Failure to login");
            }
        } catch (Exception e) {
            log.error("An error occurred during login with email: {}", request.getEmail(), e);
            throw new UserNotFoundException("Failure to login");
        }
    }

}

