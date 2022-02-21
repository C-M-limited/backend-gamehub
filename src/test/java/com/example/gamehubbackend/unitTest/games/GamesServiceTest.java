package com.example.gamehubbackend.unitTest.games;

import com.example.gamehubbackend.console.Console;
import com.example.gamehubbackend.console.ConsoleRepository;
import com.example.gamehubbackend.console.ConsoleService;
import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import com.example.gamehubbackend.console_brand.ConsoleBrandService;
import com.example.gamehubbackend.games.Games;
import com.example.gamehubbackend.games.GamesRepository;
import com.example.gamehubbackend.games.GamesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class GamesServiceTest {
    @Autowired
    private Environment env;
    @Autowired
    private ConsoleService consoleService;
    @Autowired
    private ConsoleBrandService consoleBrandService;
    @Autowired
    private GamesService gamesService;
    @MockBean
    private ConsoleRepository consoleRepository;
    @MockBean
    private static ConsoleBrandRepository consoleBrandRepository;
    @MockBean
    private GamesRepository gamesRepository;

    ConsoleBrand consoleBrand1 = new ConsoleBrand("Xbox");
    ConsoleBrand consoleBrand2 = new ConsoleBrand("Play Station");
    Console console1 = new Console("Xbox 360", consoleBrand1);
    Console console2 = new Console("PS5", consoleBrand2);

    @Test
    void getAllGames() {
        Games games1= new Games("GTA 5","image_url",console1);
        Games games2= new Games("Halo","image_url2",console1);
        doReturn(Arrays.asList(games1,games2)).when(gamesRepository).findAll();

        List<Games> result= gamesService.getAllGames();
        Assertions.assertEquals(2, result.size(), "findAll should return 2 games");
    }

    @Test
    void getAllGamesByBrand() {
        Games games1= new Games("GTA 5","image_url",console1);
        Games games2= new Games("Halo","image_url2",console1);
        doReturn(Optional.of(consoleBrand1)).when(consoleBrandRepository).findBrandByID(1);
        doReturn(Arrays.asList(games1,games2)).when(gamesRepository).findAllGamesByBrand(1);
        List<Object[]> result= gamesService.getAllGamesByBrand(1);
        Assertions.assertEquals(2, result.size(), "findAllGamesByBrand should return 2 games");
    }

    @Test
    void getAllGamesByConsole() {
        Games games1= new Games("GTA 5","image_url",console1);
        Games games2= new Games("Halo","image_url2",console1);
        doReturn(Optional.of(consoleBrand1)).when(consoleRepository).findConsoleByID(1);
        doReturn(Arrays.asList(games1,games2)).when(gamesRepository).findAllGamesByConsole(1);
        List<Games> result= gamesService.getAllGamesByConsole(1);
        Assertions.assertEquals(2, result.size(), "findAllGamesByConsole should return 2 games");
    }

    @Test
    void addGames() {
        doReturn(Optional.of(consoleBrand1)).when(consoleBrandRepository).findById(0);
        doReturn(Optional.of(console1)).when(consoleRepository).findConsoleByID(0);
        Games games1= new Games("GTA 5","image_url",console1);
        doReturn(Optional.of(console1)).when(consoleRepository).findConsoleByID(0);
        doReturn(games1).when(gamesRepository).save(any());
        //create a image
        Path path = Paths.get(env.getProperty("imageLocation")+ "testing.png");
        String name = "testing.png";
        String originalFileName = "testing.png";
        String contentType = "image/png";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile image = new MockMultipartFile(name,
                originalFileName, contentType, content);
        Games result = gamesService.addGames("GTA 5",image ,0);
        Assertions.assertEquals("GTA 5",result.getName());
    }

    @Test
    void editGames() {
        Games games1= new Games("GTA 5","image_url",console1);
        doReturn(Optional.of(games1)).when(gamesRepository).findById(0L);
        doReturn(Optional.of(console2)).when(consoleRepository).findConsoleByID(1);
        //create a image
        Path path = Paths.get(env.getProperty("imageLocation")+ "testing.png");
        String name = "testing.png";
        String originalFileName = "testing.png";
        String contentType = "image/png";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile image = new MockMultipartFile(name,
                originalFileName, contentType, content);
        Games result = gamesService.editGames(0L,"GTA 6",image ,1);
        Assertions.assertEquals("GTA 6",result.getName());
//        //edit game name
//        if (!(name != null && name.length()>0 && !Objects.equals(gameOnDB.getName(),name))){
//            throw new IllegalStateException("Not valid name");
//        }
//        gameOnDB.setName(name);
//        //edit console id
//        Optional<Console> consoleOptional= consoleRepository.findConsoleByID(console_id);
//        if (!consoleOptional.isPresent()){
//            throw new IllegalStateException("the console with id"+ console_id+"does not exits");
//        }
//        gameOnDB.setConsole(consoleOptional.get());
//        //MultipartFile
//        Random random = new Random();
//        String newFileName = String.format("%s%s",System.currentTimeMillis(),random.nextInt(100000)+".png");
//        String pathName = "/Users/leeyathei/Documents/Project/GameHub/backend-gamehub/src/main/resources/"+ newFileName;
//        try{
//            byte[] bytes = image.getBytes();
//            Files.write(Paths.get(pathName), bytes);
//            //TODO: delete the unused file
//        } catch (IOException e) {
//            throw  new IllegalStateException("the image uploading fail");
//        }
//        gameOnDB.setImage_url(pathName);
//        return gameOnDB;
    }

    @Test
    void deleteGames() {
        Games games1= new Games("GTA 5","image_url",console1);
        doReturn(Optional.of(games1)).when(gamesRepository).findById(0L);
        String result = gamesService.deleteGames(0L);
        Assertions.assertEquals("Success deleting the game with id: 0",result);
    }
}