package com.example.gamehubbackend.subscribed_post;

import com.example.gamehubbackend.game_sale_post.GameSalePost;
import com.example.gamehubbackend.game_sale_post.GameSalePostRepository;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<SubscribedPost> finAllSubscribedPostByUser(Long user_id) {
        return subscribedPostRepository.findAllSubscribedPostByUser(user_id);
    }

    public List<SubscribedPost> finAllSubscribedPostByPost(Long post_id) {
        return subscribedPostRepository.findAllSubscribedPostByPost(post_id);
    }
    //POST
    public SubscribedPost addSubscribedPost(SubscribedPost subscribedPost) {
        Long user_id=subscribedPost.getUser_Id();
        Long post_id=subscribedPost.getGame_ID();
        Optional<UserProfile> userProfileOptional=userProfileRepository.findById(user_id);
        Optional<GameSalePost> gameSalePostOptional=gameSalePostRepository.findById(post_id);
        if (!(userProfileOptional.isPresent() && gameSalePostOptional.isPresent())){
            throw new IllegalStateException("Not a valid pair");
        }
        Optional<SubscribedPost> subscribedPostOptional=subscribedPostRepository.findByPair(user_id,post_id);
        if(subscribedPostOptional.isPresent()){
            //deleteSubscribedPost(subscribedPost);
            throw new IllegalStateException("Pair already exist");
        }
        return subscribedPostRepository.save(subscribedPost);
    }
    //DELETE
    public String deleteSubscribedPost(SubscribedPost subscribedPost) {
        Long user_id=subscribedPost.getUser_Id();
        Long post_id=subscribedPost.getGame_ID();
        Optional<SubscribedPost> subscribedPostOptional=subscribedPostRepository.findByPair(user_id,post_id);
        if(!subscribedPostOptional.isPresent()){
            throw new IllegalStateException("Pair does not exist");
        }
        subscribedPostRepository.delete(subscribedPost);
        return ("Success deleting the subscribedPost");
    }
}
