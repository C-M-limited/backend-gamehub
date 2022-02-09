package com.example.gamehubbackend.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/games")
@CacheConfig(cacheNames = "games")
public class GamesController {
    private final GamesService gamesService;

    @Autowired

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping("/all")
    @Cacheable
    public List<Games> getAllGames(){
        return gamesService.getAllGames();
    }
    //TODO: to be edit not using object[]
    @GetMapping("/console_brand/{console_brand_id}")
    public List<Object[]> getAllGamesByBrand(@PathVariable ("console_brand_id") int console_brand_id){
        return gamesService.getAllGamesByBrand(console_brand_id);
    }

    @GetMapping("/console/{console_id}")
    public List<Games> getAllGamesByConsole(@PathVariable ("console_id") int console_id){
        return gamesService.getAllGamesByConsole(console_id);
    }
    @CacheEvict(  allEntries=true)
    @PostMapping(path="")
    public Games addGames(@RequestParam String name,@RequestParam MultipartFile image, @RequestParam int console_id){
        return gamesService.addGames(name, image, console_id);
    }
    @CachePut( key="#games_id")
    @PutMapping(path="")
    public Games editGames(@RequestParam long games_id,@RequestParam String name,@RequestParam MultipartFile image, @RequestParam int console_id){
        return gamesService.editGames(games_id, name, image, console_id);
    }
    //TODO: put add a param to decide change the image or not
    @CacheEvict(key="#game_id")
    @DeleteMapping(path="{game_id}")
    public String deleteGames(@PathVariable ("game_id") long game_id){
        return gamesService.deleteGames(game_id);
    }


}
