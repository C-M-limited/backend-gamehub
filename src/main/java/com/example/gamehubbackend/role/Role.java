package com.example.gamehubbackend.role;

import java.util.UUID;

public class Role {
    private final UUID role_id;
    private final String role;


    public Role(UUID role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public UUID getRole_id() {
        return role_id;
    }

    public String getRole() {
        return role;
    }
}
