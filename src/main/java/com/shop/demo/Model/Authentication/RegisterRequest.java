package com.shop.demo.Model.Authentication;

import com.shop.demo.Model.Role.Role;
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
public class RegisterRequest {
    @NotBlank(message = "user name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "please enter a valid email")
    private String email;

    private String address;
    @NotBlank(message = "password is mandatory")
    private String password;
    private Role role;
}
