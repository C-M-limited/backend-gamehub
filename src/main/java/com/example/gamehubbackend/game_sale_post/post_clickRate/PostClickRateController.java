package com.example.gamehubbackend.game_sale_post.post_clickRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/post_click_rate")
public class PostClickRateController {
    private final PostClickRateService postClickRateService;
    @Autowired
    public PostClickRateController(PostClickRateService postClickRateService) {
        this.postClickRateService = postClickRateService;
    }
    @GetMapping
    public Slice<?> getTodaysPick(){
        return postClickRateService.getTodaysPick();
    }
}
