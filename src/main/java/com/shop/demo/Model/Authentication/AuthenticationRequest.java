package com.shop.demo.Model.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "please enter a valid email")
    private String email;
    @NotBlank(message = "password is mandatory")
    String password;
}
