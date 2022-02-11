package com.example.gamehubbackend.subscribed_post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscribedPostRepository extends JpaRepository<SubscribedPost,Long> {
    @Query("SELECT sp FROM SubscribedPost sp LEFT JOIN sp.userProfile u WHERE u.id=?1")
    List<SubscribedPost> findAllSubscribedPostByUser(Long user_id);
    @Query("SELECT sp FROM SubscribedPost sp LEFT JOIN sp.gameSalePost p WHERE p.id=?1")
    List<SubscribedPost> findAllSubscribedPostByPost(Long post_id);
    @Query("SELECT sp FROM SubscribedPost sp WHERE sp.userProfile.id=?1 AND sp.gameSalePost.id=?2")
    Optional<SubscribedPost> findByPair(Long user_id, Long post_id);
}
