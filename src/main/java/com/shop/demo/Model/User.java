package com.shop.demo.Model;








import com.shop.demo.Model.Role.Role;
import com.shop.demo.Model.Token.Token;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User implements UserDetails {
    @Getter
    @Id
    @UuidGenerator
    @Column(name = "user_id")
    public String id;
    @Getter
    @NotBlank(message = "user name is mandatory")
    private String userName;
    //validate email format

    @Getter
    @NotBlank(message = "Email is mandatory")
    @Email(  regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
           message = "please enter a valid email")
    private String userEmail;
    @Getter
    @NotBlank(message = "password is mandatory")
    private String userPassword;
    @Getter
    private String address;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @Enumerated(EnumType.STRING)
    private Role role;
    //timezone is Toronto
    private String CreateAt= OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
    @Getter
    private String UpdateAt= OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;

    public User(String userId, String userName, String userEmail, String userPassword, String address, Role role) {
        this.id = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.address = address;
        this.role = role;
        this.CreateAt = OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
        this.UpdateAt = OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();;
    }


    public String getUserName_noCredential(){
        return this.userName;
    }
    public void setUpdateAt() {
        this.UpdateAt=OffsetDateTime.now(ZoneId.of("America/Toronto")).toString();
    }


    @Override
    public String toString() {

        return "User{" +
                "UserId='" + id + '\'' +
                ", UserName='" + userName + '\'' +
                ", UserEmail='" + userEmail + '\'' +
                ", UserPassword='" + userPassword + '\'' +
                ", Address='" + address + '\'' +
                ", CreatAt='" + CreateAt + '\'' +
                ", UpdateAt='" + UpdateAt + '\'' +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {

        return this.userPassword;
    }

    //using email as the register credential
    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
