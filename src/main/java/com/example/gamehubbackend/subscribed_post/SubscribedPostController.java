package com.example.gamehubbackend.subscribed_post;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<SubscribedPost> getAllSubscribedPostByUser(@PathVariable("user_id") Long user_id){
        return subscribedPostService.finAllSubscribedPostByUser(user_id);
    }
    @GetMapping(path = "/post/{post_id}")
    public List<SubscribedPost> getAllSubscribedPostByPost(@PathVariable("post_id") Long post_id){
        return subscribedPostService.finAllSubscribedPostByPost(post_id);
    }
    @PostMapping(path = "")
    public SubscribedPost addSubscribedPost(@RequestBody SubscribedPost subscribedPost){
        return subscribedPostService.addSubscribedPost(subscribedPost);
    }
    @DeleteMapping(path = "")
    public String deleteSubscribedPost(@RequestBody SubscribedPost subscribedPost){
        return subscribedPostService.deleteSubscribedPost(subscribedPost);
    }
}
