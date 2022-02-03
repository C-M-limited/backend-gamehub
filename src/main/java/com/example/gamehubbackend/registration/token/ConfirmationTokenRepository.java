package com.example.gamehubbackend.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    @Query("SELECT c from ConfirmationToken c WHERE c.token = ?1")
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update ConfirmationToken c set c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
