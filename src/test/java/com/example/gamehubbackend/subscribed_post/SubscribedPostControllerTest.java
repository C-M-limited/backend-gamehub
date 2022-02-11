package com.example.gamehubbackend.subscribed_post;

import com.example.gamehubbackend.console.Console;
import com.example.gamehubbackend.console.ConsoleRepository;
import com.example.gamehubbackend.console.ConsoleService;
import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import com.example.gamehubbackend.console_brand.ConsoleBrandService;
import com.example.gamehubbackend.game_sale_post.GameSalePost;
import com.example.gamehubbackend.game_sale_post.GameSalePostRepository;
import com.example.gamehubbackend.game_sale_post.GameSalePostService;
import com.example.gamehubbackend.games.Games;
import com.example.gamehubbackend.games.GamesRepository;
import com.example.gamehubbackend.games.GamesService;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import com.example.gamehubbackend.user_profile.UserProfileService;
import com.example.gamehubbackend.user_profile.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscribedPostControllerTest {
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
    @Autowired
    private SubscribedPostService subscribedPostService;
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
    @MockBean
    private SubscribedPostRepository subscribedPostRepository;

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
    GameSalePost post1= new GameSalePost(userProfile1,games1,500,"Yau Tong Station","Trade at 7:OO","12345678");
    @Test
    void getAllSubscribedPost() {
        SubscribedPost subscribedPost1=new SubscribedPost(post1,userProfile1);
    }

    @Test
    void getAllSubscribedPostByUser() {
    }

    @Test
    void getAllSubscribedPostByPost() {
    }

    @Test
    void addSubscribedPost() {
    }

    @Test
    void deleteSubscribedPost() {
    }
}