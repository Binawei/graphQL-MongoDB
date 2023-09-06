package com.eazipay.eazipayuser.services;

import com.eazipay.eazipayuser.dtos.LoginDTO;
import com.eazipay.eazipayuser.dtos.RegisterDTO;
import com.eazipay.eazipayuser.entities.User;
import com.eazipay.eazipayuser.repository.UserRepository;
import graphql.kickstart.annotations.GraphQLMutationResolver;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private UserRepository userRepository;
    @Override
    public Class<? extends Annotation> annotationType() {
        return Annotation.class;
    }

    public User userById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> users() {
        return userRepository.findAll();
    }

    public User createUser(RegisterDTO input) {
        User newUser = User.builder()
                .userName(input.getUsername())
                .email(input.getEmail())
                .password(input.getPassword()).build();
        return userRepository.save(newUser);
    }

    public String loginUser(LoginDTO input) {
        Optional<User> user = userRepository.findByEmail(input.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(input.getPassword())) {

            return user.get().getEmail();
        }
        return null;
    }


}
