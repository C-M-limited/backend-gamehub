package com.example.gamehubbackend.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    private String role;

    public Role(Long role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public Role() {

    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role='" + role + '\'' +
                '}';
    }
}
