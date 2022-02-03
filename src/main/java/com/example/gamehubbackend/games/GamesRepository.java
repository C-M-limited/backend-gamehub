package com.example.gamehubbackend.games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games,Long> {

    @Query ("select g from Games g where g.name=?1")
    Optional<Games> findGamesByName(String name);
//TODO: not working
    @Query ("SELECT g,c FROM Games g LEFT JOIN g.console c")
    List<Games> findAllGamesByBrand(int console_brand_id);

    @Query("select g from Games g where g.console.id=?1")
    List<Games> findAllGamesByConsole(int console_id);
}
