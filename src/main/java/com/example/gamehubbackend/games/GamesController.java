package com.example.gamehubbackend.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/games")
public class GamesController {
    private final GamesService gamesService;

    @Autowired
    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping("/all")
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

    @PostMapping(path="")
    public Games addGames(@RequestParam String name,@RequestParam MultipartFile image, @RequestParam int console_id){
        return gamesService.addGames(name, image, console_id);
    }

    @PutMapping(path="")
    public Games editGames(@RequestParam long games_id,@RequestParam String name,@RequestParam MultipartFile image, @RequestParam int console_id){
        return gamesService.editGames(games_id, name, image, console_id);
    }
    //TODO: put add a param to decide change the image or not

    @DeleteMapping(path="{game_id}")
    public String deleteGames(@PathVariable ("game_id") long game_id){
        gamesService.deleteGames(game_id);
        return ("success");
    }


}
