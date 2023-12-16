package com.shop.demo.Controller;

import com.shop.demo.Model.Role.Role;
import com.shop.demo.Model.User;
import com.shop.demo.Service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController

@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @GetMapping
    public List<User> getUsers(){

        return userServiceImpl.getUsers();
    }
    @PostMapping
    public void createNewUser(@Valid @RequestBody User user){

        userServiceImpl.saveUser(user);

    }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") String studentId){
        userServiceImpl.deleteUser(studentId);

    }
    @PutMapping(path="{userId}")
    public void updateUser(
            @PathVariable("userId") String userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Role role
    ){
        userServiceImpl.updateUser(userId,name, email,address,role);
    }
}
