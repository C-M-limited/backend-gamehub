package com.example.gamehubbackend.game_sale_post;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/game_sale_post")
@CacheConfig(cacheNames = "game_sale_post")
public class GameSalePostController {
    private final GameSalePostService gameSalePostService;

    public GameSalePostController(GameSalePostService gameSalePostService) {
        this.gameSalePostService = gameSalePostService;
    }
    @GetMapping("/all")
    @Cacheable
    public List<GameSalePost> getAllPosts(){
        return gameSalePostService.getAllPosts();
    }

    @GetMapping("/console_brand/{console_brand_id}")
    public List<GameSalePost> getAllPostsByBrand(@PathVariable ("console_brand_id") int console_brand_id){
        return gameSalePostService.getAllPostsByBrand(console_brand_id);
    }

    @GetMapping("/console/{console_id}")
    public List<GameSalePost> getAllPostsByConsole(@PathVariable("console_id") int console_id){
        return gameSalePostService.getAllPostsByConsole(console_id);
    }
    @GetMapping("games/{games_id}")
    public List<GameSalePost> getAllPostsByGame(@PathVariable("games_id")Long games_id){
        return gameSalePostService.getAllPostsByGame(games_id);
    }
    @GetMapping("user/{user_id}")
    public  List<GameSalePost> getAllPostsByUser(@PathVariable("user_id") Long user_id){
        return gameSalePostService.getAllPostByUser(user_id);
    }
    @PostMapping(path = "")
    @CacheEvict(  allEntries=true)
    public GameSalePost addPosts(@RequestBody GameSalePost gameSalePost){
        return gameSalePostService.addPosts(gameSalePost);
    }
    //    TODO: Cache
//    @CachePut(key ="#g" )
    @PutMapping(path="")
    public GameSalePost editPosts(@RequestBody GameSalePost gameSalePost){
        return gameSalePostService.editPosts(gameSalePost);
    }
    @DeleteMapping("{posts_id}")
    @CacheEvict(key="#posts_id")
    public String deletePosts(@RequestParam ("posts_id") long posts_id ){
        return gameSalePostService.deletePosts(posts_id);
    }


//        @GetMapping("/all")
//    @Cacheable
//    public List<Games> getAllGames(){
//        return gamesService.getAllGames();
//    }
//    //TODO: to be edit not using object[]
//    @GetMapping("/console_brand/{console_brand_id}")
//    public List<Object[]> getAllGamesByBrand(@PathVariable ("console_brand_id") int console_brand_id){
//        return gamesService.getAllGamesByBrand(console_brand_id);
//    }
}