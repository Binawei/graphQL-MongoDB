package com.eazipay.eazipayuser.exceptions;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphQLExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    public ThrowableGraphQLError handle(UserNotFoundException e){
        return new ThrowableGraphQLError(e);
    }
}
