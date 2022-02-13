package com.example.gamehubbackend.jwt.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    @Query("SELECT t FROM RefreshToken t WHERE t.refreshToken=?1")
    Optional<RefreshToken> findByToken(String token);
    @Modifying
    @Query("DELETE FROM RefreshToken t WHERE t.refreshToken=?1")
    void deleteByToken(String token);
}
