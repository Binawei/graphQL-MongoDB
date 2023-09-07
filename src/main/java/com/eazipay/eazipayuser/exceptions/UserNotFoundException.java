package com.eazipay.eazipayuser.exceptions;
;

public class UserNotFoundException extends  RuntimeException{
    private String message;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
