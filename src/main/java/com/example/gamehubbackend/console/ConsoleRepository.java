package com.example.gamehubbackend.console;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    @Query("SELECT c FROM Console c WHERE c.name = ?1")
    Optional<Console> findConsoleByName(String name);

    @Query("SELECT c FROM Console c WHERE c.id = ?1")
    Optional<Console> findConsoleByID(Integer console_id);

}
