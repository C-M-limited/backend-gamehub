package com.example.gamehubbackend.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public String createRole(@RequestBody Role role) {
        try {
            roleService.createRole(role);
            return "create role successfully";
        }
        catch(Exception e) {
            return "failed";
        }
    }
}
