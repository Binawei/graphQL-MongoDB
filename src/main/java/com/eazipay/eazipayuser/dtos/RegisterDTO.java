package com.eazipay.eazipayuser.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class RegisterDTO {
    private String username;
    @Email
    @NotNull
    private String email;
    @Size(min = '4', max = '8')
    @NotNull
    private String password;
    private String phoneNumber;
}
