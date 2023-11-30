package com.shop.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController

@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getUsers(){

        return userService.getUsers();
    }
    @PostMapping
    public void createNewUser( @Valid @RequestBody User user){

        userService.createNewUser(user);

    }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") String studentId){
        userService.deleteUser(studentId);

    }
    @PutMapping(path="{userId}")
    public void updateUser(
            @PathVariable("userId") String userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address
    ){
        userService.updateUser(userId,name, email,address);
    }
}
