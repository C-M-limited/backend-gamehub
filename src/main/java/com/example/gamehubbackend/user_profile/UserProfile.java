package com.example.gamehubbackend.user_profile;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private int role_id;
    private String user_name;
    private String email;
    private String password;
    private Boolean verified;
    private Timestamp last_login;
    private Timestamp created_at;
    private Timestamp updated_at;

    public UserProfile(Long user_id, int role_id, String user_name, String email, String password, Boolean verified, Timestamp last_login, Timestamp created_at, Timestamp updated_at) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.last_login = last_login;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public UserProfile() {
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "user_id=" + user_id +
                ", role_id=" + role_id +
                ", user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verified=" + verified +
                ", last_login=" + last_login +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
