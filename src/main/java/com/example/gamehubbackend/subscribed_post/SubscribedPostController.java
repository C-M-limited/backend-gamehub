package com.example.gamehubbackend.subscribed_post;

import com.example.gamehubbackend.games.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/subscribed_post")
public class SubscribedPostController {
    private final SubscribedPostService subscribedPostService;

    @Autowired
    public SubscribedPostController(SubscribedPostService subscribedPostService) {
        this.subscribedPostService = subscribedPostService;
    }
    @GetMapping(path = "all")
    public List<SubscribedPost> getAllSubscribedPost(){
        return subscribedPostService.findAll();
    }
    @GetMapping(path = "/user/{user_id}")
    public List<?> getAllSubscribedPostByUser(@PathVariable("user_id") Long user_id){
        return subscribedPostService.finAllSubscribedPostByUser(user_id);
    }
    @GetMapping(path = "/post/{post_id}")
    public List<SubscribedPost> getAllSubscribedPostByPost(@PathVariable("post_id") Long post_id){
        return subscribedPostService.finAllSubscribedPostByPost(post_id);
    }
    @GetMapping(path = "/user&post/")
    public Boolean getGamesByKeyword(@RequestParam("userId") Long userId, @RequestParam("postId") Long postId){
        return subscribedPostService.isLikedByUser(userId,postId);
    }
    @PostMapping(path = "")
    public ResponseEntity addSubscribedPost(@RequestBody SubscribedPost subscribedPost){
        return subscribedPostService.addSubscribedPost(subscribedPost);
    }
    @DeleteMapping(path = "")
    public String deleteSubscribedPost(@RequestBody SubscribedPost subscribedPost){
        return subscribedPostService.deleteSubscribedPost(subscribedPost);
    }

}
