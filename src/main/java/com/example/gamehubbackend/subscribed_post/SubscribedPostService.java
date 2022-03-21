package com.example.gamehubbackend.subscribed_post;

import com.example.gamehubbackend.game_sale_post.GameSalePost;
import com.example.gamehubbackend.game_sale_post.GameSalePostRepository;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscribedPostService {
    private final SubscribedPostRepository subscribedPostRepository;
    private final UserProfileRepository userProfileRepository;
    private final GameSalePostRepository gameSalePostRepository;

    @Autowired
    public SubscribedPostService(SubscribedPostRepository subscribedPostRepository, UserProfileRepository userProfileRepository, GameSalePostRepository gameSalePostRepository) {
        this.subscribedPostRepository = subscribedPostRepository;
        this.userProfileRepository = userProfileRepository;
        this.gameSalePostRepository = gameSalePostRepository;
    }

    //GET
    public List<SubscribedPost> findAll() {
        return subscribedPostRepository.findAll();
    }

    public List<?> finAllSubscribedPostByUser(Long user_id) {
        return subscribedPostRepository.findAllSubscribedPostByUser(user_id);
    }

    public List<SubscribedPost> finAllSubscribedPostByPost(Long post_id) {
        return subscribedPostRepository.findAllSubscribedPostByPost(post_id);
    }

    public Boolean isLikedByUser(Long userId, Long postId) {
        Optional<SubscribedPost> subscribedPostOptional= subscribedPostRepository.findByPair(userId,postId);
        if (subscribedPostOptional.isPresent()){
            return true;
        }
        return false;
    }
    //POST
    public ResponseEntity addSubscribedPost(SubscribedPost subscribedPost) {
        Long user_id=subscribedPost.getUser_Id();
        Long post_id=subscribedPost.getGame_ID();
        Optional<UserProfile> userProfileOptional=userProfileRepository.findById(user_id);
        Optional<GameSalePost> gameSalePostOptional=gameSalePostRepository.findById(post_id);
        if (!(userProfileOptional.isPresent() && gameSalePostOptional.isPresent())){
            throw new IllegalStateException("Not a valid pair");
        }
        Optional<SubscribedPost> subscribedPostOptional=subscribedPostRepository.findByPair(user_id,post_id);
        if(subscribedPostOptional.isPresent()){
            subscribedPostRepository.delete(subscribedPostOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }
        subscribedPostRepository.save(subscribedPost);
        return ResponseEntity.status(HttpStatus.OK).body("Added");
    }
    //DELETE
    public String deleteSubscribedPost(SubscribedPost subscribedPost) {
        Long user_id=subscribedPost.getUser_Id();
        Long post_id=subscribedPost.getGame_ID();
        Optional<SubscribedPost> subscribedPostOptional=subscribedPostRepository.findByPair(user_id,post_id);
        if(!subscribedPostOptional.isPresent()){
            throw new IllegalStateException("Pair does not exist");
        }
        subscribedPostRepository.delete(subscribedPostOptional.get());
        return ("Success deleting the subscribedPost");
    }


}
