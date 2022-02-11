package com.example.gamehubbackend.game_sale_post;

import com.example.gamehubbackend.console.Console;
import com.example.gamehubbackend.console.ConsoleRepository;
import com.example.gamehubbackend.console.ConsoleService;
import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import com.example.gamehubbackend.console_brand.ConsoleBrandService;
import com.example.gamehubbackend.games.Games;
import com.example.gamehubbackend.games.GamesRepository;
import com.example.gamehubbackend.games.GamesService;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import com.example.gamehubbackend.user_profile.UserProfileService;
import com.example.gamehubbackend.user_profile.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
class GameSalePostServiceTest {
    @Autowired
    private ConsoleService consoleService;
    @Autowired
    private ConsoleBrandService consoleBrandService;
    @Autowired
    private GamesService gamesService;
    @Autowired
    private GameSalePostService gameSalePostService;
    @Autowired
    private UserProfileService userProfileService;
    @MockBean
    private ConsoleRepository consoleRepository;
    @MockBean
    private static ConsoleBrandRepository consoleBrandRepository;
    @MockBean
    private GamesRepository gamesRepository;
    @MockBean
    private GameSalePostRepository gameSalePostRepository;
    @MockBean
    private UserProfileRepository userProfileRepository;

    ConsoleBrand consoleBrand1 = new ConsoleBrand("Xbox");
    ConsoleBrand consoleBrand2 = new ConsoleBrand("Play Station");
    Console console1 = new Console("Xbox 360", consoleBrand1);
    Console console2 = new Console("PS5", consoleBrand2);
    Games games1= new Games("GTA 5","image_url",console1);
    UserProfile userProfile1 = new UserProfile(
            "martin",
            "tsang",
            "martin.tsang@gmail.com",
            "password",
            UserRole.USER
    );
    @Test
    void getAllPosts() {
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        GameSalePost post2= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        doReturn(Arrays.asList(post1,post2)).when(gameSalePostRepository).findAll();

        List<GameSalePost> result = gameSalePostService.getAllPosts();
        Assertions.assertEquals(2, result.size(), "findAll should return 2 posts");

    }

    @Test
    void getAllPostsByBrand() {
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        GameSalePost post2= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        doReturn(Optional.of(consoleBrand1)).when(consoleBrandRepository).findBrandByID(1);
        doReturn(Arrays.asList(post1,post2)).when(gameSalePostRepository).findAllPostsByBrand(1);
        List<GameSalePost> result= gameSalePostService.getAllPostsByBrand(1);
        Assertions.assertEquals(2,result.size(),"findAllPostsByBrand should return 2 posts");
    }

    @Test
    void getAllPostsByConsole() {
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        GameSalePost post2= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        doReturn(Optional.of(console1)).when(consoleRepository).findConsoleByID(1);
        doReturn(Arrays.asList(post1,post2)).when(gameSalePostRepository).findAllPostsByConsole(1);
        List<GameSalePost> result= gameSalePostService.getAllPostsByConsole(1);
        Assertions.assertEquals(2,result.size(),"findAllPostsByConsole should return 2 posts");
    }

    @Test
    void getAllPostsByGame() {
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        GameSalePost post2= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        doReturn(Optional.of(games1)).when(gamesRepository).findById(1L);
        doReturn(Arrays.asList(post1,post2)).when(gameSalePostRepository).findAllPostsByGames(1L);
        List<GameSalePost> result= gameSalePostService.getAllPostsByGame(1L);
        Assertions.assertEquals(2,result.size(),"findAllPostsByGame should return 2 posts");
    }

    @Test
    void getAllPostByUser() {
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        GameSalePost post2= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        doReturn(Optional.of(userProfile1)).when(userProfileRepository).findById(1L);
        doReturn(Arrays.asList(post1,post2)).when(gameSalePostRepository).findAllPostsByUser(1L);
        List<GameSalePost> result= gameSalePostService.getAllPostByUser(1L);
        Assertions.assertEquals(2,result.size(),"findAllPostsByUser should return 2 posts");
    }

    @Test
    void addPosts() {
        doReturn(Optional.of(games1)).when(gamesRepository).findById(null);
        doReturn(Optional.of(userProfile1)).when(userProfileRepository).findById(null);
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        doReturn(post1).when(gameSalePostRepository).save(post1);
        GameSalePost result = gameSalePostService.addPosts(post1);
        Assertions.assertEquals(500,result.getPrice(),"Should save a post with price 500");
    }

    @Test
    void editPosts() {
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        GameSalePost post2= new GameSalePost(userProfile1,games1,600,"Yau Tong Station2","Trade at 7:OO","12345678");
        doReturn(Optional.of(post1)).when(gameSalePostRepository).findById(null);
        GameSalePost result= gameSalePostService.editPosts(post2);
        Assertions.assertEquals(600,result.getPrice(),"The price should be changed to 600");
    }

    @Test
    void deletePosts() {
        GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
        doReturn(Optional.of(post1)).when(gameSalePostRepository).findById(0L);
        String result= gameSalePostService.deletePosts(0L);
        Assertions.assertEquals("Success deleting the post with id: 0",result,"The result should show successful");
    }
}