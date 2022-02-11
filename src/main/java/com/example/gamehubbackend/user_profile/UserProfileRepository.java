package com.example.gamehubbackend.user_profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findUserProfileByEmail(String email);

    @Query("SELECT u FROM UserProfile u WHERE u.id = ?1")
    Optional<UserProfile> findUserProfileByID(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UserProfile u SET u.enabled = TRUE WHERE u.email = ?1")
    int enableUserProfile(String email);
}
