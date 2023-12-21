package com.shop.demo.Model.Role;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;


import static com.shop.demo.Model.Role.Permission.ADMIN_CREATE;
import static com.shop.demo.Model.Role.Permission.ADMIN_DELETE;
import static com.shop.demo.Model.Role.Permission.ADMIN_READ;
import static com.shop.demo.Model.Role.Permission.ADMIN_UPDATE;
import static com.shop.demo.Model.Role.Permission.MANAGER_CREATE;
import static com.shop.demo.Model.Role.Permission.MANAGER_DELETE;
import static com.shop.demo.Model.Role.Permission.MANAGER_READ;
import static com.shop.demo.Model.Role.Permission.MANAGER_UPDATE;


@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    );


    @Getter
    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = new java.util.ArrayList<>(getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;



    }
}