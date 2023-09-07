package com.eazipay.eazipayuser.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Builder
public class LoginDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 6)
    private String password;
}
