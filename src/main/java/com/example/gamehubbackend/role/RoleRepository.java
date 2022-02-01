package com.example.gamehubbackend.role;

import com.example.gamehubbackend.user_profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT u FROM Role u WHERE u.name = ?1")
    Optional<Role> findRoleByName(String name);

    @Query("SELECT u FROM Role u WHERE u.role_id = ?1")
    Optional<Role> findRoleByID(Long id);
}
