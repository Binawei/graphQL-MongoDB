package com.eazipay.eazipayuser.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterDTO {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
