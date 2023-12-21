package com.shop.demo.Service;

import com.shop.demo.Model.Role.Role;
import com.shop.demo.Model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
//    Role saveRole(Role role);
//    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void deleteUser(String userId);
    void updateUser(String userId, String name, String email, String address,Role role);

}
