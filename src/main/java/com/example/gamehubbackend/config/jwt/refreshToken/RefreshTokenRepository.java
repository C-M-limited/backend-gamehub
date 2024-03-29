package com.example.gamehubbackend.config.jwt.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    @Query("SELECT t FROM RefreshToken t WHERE t.refreshToken=?1")
    Optional<RefreshToken> findByToken(String token);

    @Query("SELECT t FROM RefreshToken t WHERE t.user_id=?1")
    Optional<RefreshToken> findByUserId(Long id);
//    Delete
    @Modifying
    @Query("DELETE FROM RefreshToken t WHERE t.refreshToken=?1")
    void deleteByToken(String token);
    @Modifying
    @Query("DELETE FROM RefreshToken t WHERE t.user_id = ?1")
    void deleteByUserId(Long id);
}
