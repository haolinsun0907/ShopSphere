package com.shop.demo.Repo;

import com.shop.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,String> {

    Optional<User> findUserByUserEmail(String email);
    Optional<User> findUserByUserName(String userName);
}
