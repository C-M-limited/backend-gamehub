package com.example.gamehubbackend.game_sale_post;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface GameSalePostRepository extends JpaRepository<GameSalePost,Long> {
    @Query("SELECT p FROM GameSalePost p LEFT JOIN p.games g LEFT JOIN g.console c WHERE c.console_brand.id=?1")
    List<GameSalePost> findAllPostsByBrand(int console_brand_id);

    @Query("SELECT p FROM GameSalePost p LEFT JOIN p.games g WHERE g.console.id=?1")
    List<GameSalePost> findAllPostsByConsole(int console_id);

    @Query("SELECT p FROM GameSalePost p WHERE p.games.id=?1")
    List<GameSalePost> findAllPostsByGames(Long games_id);

    @Query("SELECT p FROM GameSalePost p WHERE p.userProfile.id=?1")
    List<GameSalePost> findAllPostsByUser(Long user_id);

    @Query("SELECT new com.example.gamehubbackend.game_sale_post.GameSaleResponse(p, u.lastName, g.name) FROM GameSalePost p LEFT JOIN p.games g LEFT JOIN p.userProfile u WHERE g.console.console_brand.name = :category")
    Slice<?> findAllPostWithCategoryWithSorting(String category, Pageable range);

    @Query("SELECT new com.example.gamehubbackend.game_sale_post.GameSaleResponse(p, u.lastName, g.name) FROM GameSalePost p LEFT JOIN p.games g LEFT JOIN p.userProfile u")
    Slice<?> findAllPostWithSorting(Pageable range);

    @Query("SELECT new com.example.gamehubbackend.game_sale_post.GameSaleResponse(p, u.lastName, g.name) FROM GameSalePost p LEFT JOIN p.games g LEFT JOIN p.userProfile u WHERE p.id=:game_sale_post_id")
    List<?> findPostByGameSalePostID(Long game_sale_post_id);
}
