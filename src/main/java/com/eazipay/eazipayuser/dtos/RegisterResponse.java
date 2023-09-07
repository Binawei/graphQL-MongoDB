package com.eazipay.eazipayuser.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {
    private String username;
    private  String email;
}
