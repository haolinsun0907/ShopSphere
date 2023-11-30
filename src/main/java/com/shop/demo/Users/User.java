package com.shop.demo.Users;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;




import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "users")

public class User {
    @Id

    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")

    @Column(name = "user_id")
    private String UserId;
    @NotBlank(message = "user name is mandatory")
    private String UserName;
    //validate email format

    @NotBlank(message = "Email is mandatory")
    @Email(        regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
           message = "please enter a valid email")
    private String UserEmail;
    @NotBlank(message = "password is mandatory")
    private String UserPassword;
    private String Address;

    //timezone is Toronto
    private String CreateAt= OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
    private String UpdateAt= OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;


    public User() {
    }

    public User(String userId, String userName, String userEmail, String userPassword, String address, String createAt, String updateAt) {
        this.UserId = userId;
        this.UserName = userName;
        this.UserEmail = userEmail;
        this.UserPassword = userPassword;
        this.Address = address;
        this.CreateAt = OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
        this.UpdateAt = OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
    }



    public String getUserId() {
        return UserId;
    }

//    public void setUserId(String userId) {
//        this.UserId = userId;
//    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        this.UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) throws NoSuchAlgorithmException {

        this.UserPassword = hashUserPassword(userPassword);
    }
    //encode password by "SHA-256"
    private String hashUserPassword(String userPassword) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] encodePassword = md.digest(userPassword.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1,encodePassword);
        StringBuilder hexPassword = new StringBuilder(number.toString(16));
        while (hexPassword.length() < 64)
        {
            hexPassword.insert(0, '0');
        }


        return hexPassword.toString();
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getCreatAt() {
        return CreateAt;
    }

    public void setCreatAt() {
        this.CreateAt = OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();
    }



    public String getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt() {
        this.UpdateAt=OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", Address='" + Address + '\'' +
                ", CreatAt='" + CreateAt + '\'' +
                ", UpdateAt='" + UpdateAt + '\'' +
                '}';
    }
}
