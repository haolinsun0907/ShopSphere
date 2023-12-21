package com.shop.demo.Service;

import com.shop.demo.Model.Role.Role;
import com.shop.demo.Model.User;

import com.shop.demo.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service @RequiredArgsConstructor
@Transactional @Slf4j

public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
//    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    // add user to the authority group
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByUserEmail(userEmail);
        if(user.isEmpty()){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}", userEmail);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.get().getAuthorities().forEach( role -> {
                authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            });
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(),user.get().getUserPassword(),authorities);
        }

    }
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUserName_noCredential());
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        Optional<User> userPresent = userRepo.findUserByUserEmail(user.getUserEmail());
        if(userPresent.isPresent()){
            throw new IllegalStateException("email taken");
        }
        return userRepo.save(user);
    }
//
//    @Override
//    public Role saveRole(Role role) {
//        log.info("Saving new role {} to the database", role.name());
//        return roleRepo.save(role);
//    }
//
//    @Override
//    public void addRoleToUser(String userMail, String roleName) {
//        log.info("Adding role {} to user {}", roleName, userMail);
//        Optional<User> user = userRepo.findUserByUserEmail(userMail);
//        Role role = roleRepo.findByRoleName(roleName);
//        if(user != null && role != null){
//            user.get().getAuthorities().add(role);
//        }
//        else{
//            if (user == null){
//                log.info("user {} is not found ", userMail);
//            } else {
//                log.info("Role {} is not defined ", roleName);
//            }
//
//        }
//
//    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        Optional<User> user =userRepo.findUserByUserName(username);
        if (user.isPresent()) {
            return user.get();
        }
        else{
            throw new UsernameNotFoundException("user is not exist");
        }
    }

    public List<User> getUsers(){
        log.info("Fetching all users");
        return userRepo.findAll();
    }


    public void deleteUser(String studentId) {

        boolean exists = userRepo.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Student with id"+ studentId +"does not exists");

        }
        userRepo.deleteById(studentId);
    }

    @Override @Transactional
    public void updateUser(String userId, String name, String email, String address, Role role) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new IllegalStateException("User with id "+userId+"dose not exist"));

        if (email != null && !email.isEmpty() && !Objects.equals(user.getUserEmail(),email)){

            user.setUserEmail(email);
        }
        else{
            log.info("Email is none or empty");
        }
        if (name != null && !name.isEmpty() && !Objects.equals(user.getUserName_noCredential(),name)){
            user.setUserName(name);

        }
        else{
            log.info("userName is none or empty");
        }
        if (address != null && !address.isEmpty() && !Objects.equals(user.getAddress(),address)){
            user.setAddress(address);
        }
        else{
            log.info("userAddress is none or empty");
        }
//        if (role != null &&  !user.getRoles().contains(role)){
//            user.getRoles().add(role);
//        }  else{
//            log.info("role is existed");
//        }

        user.setUpdateAt();
    }







}
