package com.shop.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){

        return userRepository.findAll();
    }
    public void createNewUser(User user){
        Optional<User> userOptional = userRepository.findUserByUserEmail(user.getUserEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);


    }

    public void deleteUser(String studentId) {
//        userRepository.findById(studentId);
        boolean exists = userRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Student with id"+ studentId +"does not exists");

        }
        userRepository.deleteById(studentId);
    }
    @Transactional

    public void updateUser(String userId, String name, String email, String address){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException("User with id "+userId+"dose not exist"));

        if (email != null && !email.isEmpty() && !Objects.equals(user.getUserEmail(),email)){

            user.setUserEmail(email);


        }
        if (name != null && !name.isEmpty() && !Objects.equals(user.getUserName(),name)){
            user.setUserName(name);


        }
        if (address != null && !address.isEmpty() && !Objects.equals(user.getAddress(),address)){
            user.setAddress(address);


        }
        user.setUpdateAt();
    }


}
