package com.example.gamehubbackend.game_sale_post.post_clickRate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostClickRateRepository extends JpaRepository<PostClickRate,Long> {
    @Query("SELECT new com.example.gamehubbackend.game_sale_post.CustomObject.GameSaleResponseWithConsoleBrand(p, u.lastName, g.name, g.image_url,b.name) FROM PostClickRate c LEFT JOIN c.gameSalePost p LEFT JOIN p.games g LEFT JOIN p.userProfile u LEFT JOIN g.console co LEFT JOIN co.console_brand b")
    Slice<?> findTodaysPick(Pageable range);
    @Query("SELECT c FROM PostClickRate c where c.gameSalePost.id=?1")
    Optional<Object> findByPostID(Long id);
}
