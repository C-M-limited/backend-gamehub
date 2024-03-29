package com.example.gamehubbackend.games;

import com.example.gamehubbackend.config.awsS3.StorageService;
import com.example.gamehubbackend.console.Console;
import com.example.gamehubbackend.console.ConsoleRepository;
import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
//@CacheConfig(cacheNames = "games")
public class GamesService {
    private final GamesRepository gamesRepository;
    private final ConsoleRepository consoleRepository;
    private final ConsoleBrandRepository consoleBrandRepository;
    private final StorageService storageService;

    @Autowired
    private Environment env;

    @Autowired
    public GamesService(GamesRepository gamesRepository, ConsoleRepository consoleRepository, ConsoleBrandRepository consoleBrandRepository, StorageService storageService) {
        this.gamesRepository = gamesRepository;
        this.consoleRepository = consoleRepository;
        this.consoleBrandRepository = consoleBrandRepository;
        this.storageService = storageService;
    }
    //@Cacheable
    public List<Games> getAllGames() {
        return gamesRepository.findAll();
    }

    public List<Object[]> getAllGamesByBrand(int console_brand_id) {
        Optional<ConsoleBrand> optionalConsoleBrand=consoleBrandRepository.findBrandByID(console_brand_id);
        if (!optionalConsoleBrand.isPresent()){
            throw new IllegalStateException("Console Brand does not exist");
        }
        return gamesRepository.findAllGamesByBrand(console_brand_id);
    }
//    @Cacheable(value = "games")
    public List<Games> getAllGamesByConsole(int console_id) {
        Optional<Console> optionalConsole=consoleRepository.findConsoleByID(console_id);
        if (!optionalConsole.isPresent()){
            throw new IllegalStateException("Console does not exist");
        }
        return gamesRepository.findAllGamesByConsole(console_id);
    }

    public Page<Games> getGamesByPage(int page, int size, String sortBy, String category) {
        Pageable range= PageRequest.of(page,size, Sort.by(sortBy).descending());
        switch(category){
            case "all" :
                return gamesRepository.findAll(range);
            case "playstation" :
                return gamesRepository.findByConsoleId(1,range);
            case "nintendo" :
                return gamesRepository.findByConsoleId(2,range);
            case "xbox" :
                return gamesRepository.findByConsoleId(3,range);
        }

        return null;
    }
    public Slice<Games> getGamesByKeyword(String keyword, int page) {
        Pageable range= PageRequest.of(page,10);
        if (keyword==""){
            return gamesRepository.findAll(range);
        }

        return gamesRepository.findGamesByKeyword(keyword,range);
    }

    public Optional<Games> getGamesById(Long game_id) {
        return gamesRepository.findById(game_id);
    }
    //POST

    //@CacheEvict(  allEntries=true)
//    @CachePut(key="#game.id")
    public Games addGames(String name, MultipartFile image, int console_id) throws Exception {
        //check console is exist
        Optional<Console> optionalConsole = consoleRepository.findConsoleByID(console_id);
        if (!optionalConsole.isPresent()) {
            throw new IllegalStateException("Console does not exist");
        }
        //check name is not exist
        Optional<Games> gamesOptional= gamesRepository.findGamesByName(name);
        if (gamesOptional.isPresent()){
            throw  new IllegalStateException("the games already exist");
        }
        //upload Image
        String pathName = storageService.uploadFile(image);
        //save the game object
        Games game = new Games();
        game.setName(name);
        game.setConsole(optionalConsole.get());
        game.setImage_url(pathName);

        return gamesRepository.save(game);
    }

    @Transactional
    //@CachePut( key="#games_id")
    public Games editGames(long games_id, String name, MultipartFile image, int console_id) {
        Games gameOnDB = gamesRepository.findById(games_id)
                .orElseThrow(()->new IllegalStateException(("the games with id "+games_id+" does not exist")));
        //edit game name
        if (!(name != null && name.length()>0 && !Objects.equals(gameOnDB.getName(),name))){
            throw new IllegalStateException("Not valid name");
        }
        gameOnDB.setName(name);
        //edit console id
        Optional<Console> consoleOptional= consoleRepository.findConsoleByID(console_id);
        if (!consoleOptional.isPresent()){
            throw new IllegalStateException("the console with id"+ console_id+"does not exist");
        }
        gameOnDB.setConsole(consoleOptional.get());
        //MultipartFile
        Random random = new Random();
        String newFileName = String.format("%s%s",System.currentTimeMillis(),random.nextInt(100000)+".png");
        String pathName = env.getProperty("imageLocation")+ newFileName;
        try{
            byte[] bytes = image.getBytes();
            Files.write(Paths.get(pathName), bytes);
            //TODO: delete the unused file
        } catch (IOException e) {
            throw  new IllegalStateException("the image uploading fail");
        }
        gameOnDB.setImage_url(pathName);
        return gameOnDB;



    }
    //@CacheEvict(key="#game_id")
    public String deleteGames(long game_id) {
        Optional<Games> gamesOptional= gamesRepository.findById(game_id);
        if (!gamesOptional.isPresent()){
            throw  new IllegalStateException("the games with id "+game_id+" does not exist");
        }
        gamesRepository.deleteById(game_id);
        return ("Success deleting the game with id: "+game_id);
    }


}
