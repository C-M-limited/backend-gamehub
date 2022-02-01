package com.example.gamehubbackend.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // GET
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // POST
    public void createRole(Role role) {
        Optional<Role> roleOptional = roleRepository.findRoleByName(role.getName());
        if (roleOptional.isPresent()) {
            throw new IllegalStateException("role already exist");
        }
        roleRepository.save(role);
    }
}
