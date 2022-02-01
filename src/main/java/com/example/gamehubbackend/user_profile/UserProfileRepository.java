package com.example.gamehubbackend.user_profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    @Query("SELECT u FROM UserProfile u WHERE u.user_name = ?1")
    Optional<UserProfile> findUserProfileByName(String name);

    @Query("SELECT u FROM UserProfile u WHERE u.user_id = ?1")
    Optional<UserProfile> findUserProfileByID(Long id);
}
