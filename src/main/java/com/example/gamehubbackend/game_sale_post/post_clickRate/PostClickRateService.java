package com.example.gamehubbackend.game_sale_post.post_clickRate;

import com.example.gamehubbackend.game_sale_post.GameSalePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostClickRateService {
    private final PostClickRateRepository postClickRateRepository;
    @Autowired
    public PostClickRateService(PostClickRateRepository postClickRateRepository) {
        this.postClickRateRepository = postClickRateRepository;
    }

    public Slice<?> getTodaysPick(){
        Pageable range= PageRequest.of(0,5, Sort.by("clickRate").descending());
        return postClickRateRepository.findTodaysPick(range);
    }

    //Put
    @Transactional
    public boolean addClickRate(Long id){
        PostClickRate postOnDB = (PostClickRate) postClickRateRepository.findByPostID(id)
                .orElseThrow(()->new IllegalStateException(("the posts with id "+id+" does not exist")));
        postOnDB.setClickRate(postOnDB.getClickRate()+1);
        return true;
    }
}
