package com.example.gamehubbackend.games;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games,Long> {

    @Query ("select g from Games g where g.name=?1")
    Optional<Games> findGamesByName(String name);
//TODO: to be edit not using object[]
    @Query ("SELECT g FROM Games g left join g.console c where c.console_brand.id=?1")
    List<Object[]> findAllGamesByBrand(int console_brand_id);

    @Query("select g from Games g where g.console.id=?1")
    List<Games> findAllGamesByConsole(int console_id);

    @Query("SELECT g FROM Games g where g.console.id=?1")
    Page<Games> findByConsoleId(int category, Pageable range);
}
