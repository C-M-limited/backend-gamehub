package com.example.gamehubbackend.user;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private int role_id;
    private String user_name;
    private String email;
    private String password;
    private Boolean verified;
    private Timestamp last_login;
    private Timestamp created_at;
    private Timestamp updated_at;

    public User(Long id, int role_id, String user_name, String email, String password, Boolean verified, Timestamp last_login, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.role_id = role_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.last_login = last_login;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getVerified() {
        return verified;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
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
