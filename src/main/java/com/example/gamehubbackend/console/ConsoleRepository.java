package com.example.gamehubbackend.console;

import com.example.gamehubbackend.console_brand.ConsoleBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    @Query("SELECT s FROM Console s WHERE s.console_name = ?1")
    Optional<Console> findConsoleByName(String name);

    @Query("SELECT s FROM Console s WHERE s.console_id = ?1")
    Optional<Console> findConsoleByID(Integer console_id);
}
