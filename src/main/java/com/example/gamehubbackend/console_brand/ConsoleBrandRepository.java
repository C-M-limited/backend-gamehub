package com.example.gamehubbackend.console_brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsoleBrandRepository extends JpaRepository<ConsoleBrand, Integer> {
    @Query("SELECT s FROM ConsoleBrand s WHERE s.console_brand_name = ?1")
    Optional<ConsoleBrand> findBrandByName(String name);
}