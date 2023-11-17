package Users;

import java.security.PrivateKey;

public class User {
    private String UserId;
    private String UserName;
    private String UserEmail;
    private String UserPassword;
    private String Address;
    private String CreatAt;
    private String UpdateAt;


    public User() {
    }

    public User(String userId, String userName, String userEmail, String userPassword, String address, String creatAt, String updateAt) {
        this.UserId = userId;
        this.UserName = userName;
        this.UserEmail = userEmail;
        this.UserPassword = userPassword;
        this.Address = address;
        this.CreatAt = creatAt;
        this.UpdateAt = updateAt;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

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

    public void setUserPassword(String userPassword) {
        this.UserPassword = userPassword;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getCreatAt() {
        return CreatAt;
    }

    public void setCreatAt(String creatAt) {
        this.CreatAt = creatAt;
    }

    public String getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.UpdateAt = updateAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", Address='" + Address + '\'' +
                ", CreatAt='" + CreatAt + '\'' +
                ", UpdateAt='" + UpdateAt + '\'' +
                '}';
    }
}
