package com.eazipay.eazipayuser.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthPayload {
    private String token;
    private String  email;
    private String username;
}
