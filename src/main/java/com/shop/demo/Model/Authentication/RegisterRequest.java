package com.shop.demo.Model.Authentication;

import com.shop.demo.Model.Role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String address;
    private String password;
    private Role role;
}
