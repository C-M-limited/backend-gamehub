package com.example.gamehubbackend.game_sale_post;

import com.example.gamehubbackend.console.Console;
import com.example.gamehubbackend.console.ConsoleRepository;
import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import com.example.gamehubbackend.games.Games;
import com.example.gamehubbackend.games.GamesRepository;
import com.example.gamehubbackend.config.jwt.JwtUtil;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class GameSalePostService {
    private final GameSalePostRepository gameSalePostRepository;
    private final GamesRepository gamesRepository;
    private final ConsoleRepository consoleRepository;
    private final ConsoleBrandRepository consoleBrandRepository;
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public GameSalePostService(GameSalePostRepository gameSalePostRepository, GamesRepository gamesRepository, ConsoleRepository consoleRepository, ConsoleBrandRepository consoleBrandRepository, UserProfileRepository userProfileRepository) {
        this.gameSalePostRepository = gameSalePostRepository;
        this.gamesRepository = gamesRepository;
        this.consoleRepository = consoleRepository;
        this.consoleBrandRepository = consoleBrandRepository;
        this.userProfileRepository = userProfileRepository;
    }
    public List<GameSalePost> getAllPosts() {
        return gameSalePostRepository.findAll();
    }

    public List<GameSalePost> getAllPostsByBrand(int console_brand_id) {
        Optional<ConsoleBrand> optionalConsoleBrand=consoleBrandRepository.findBrandByID(console_brand_id);
        if (!optionalConsoleBrand.isPresent()){
            throw new IllegalStateException("Console Brand does not exist");
        }
        return gameSalePostRepository.findAllPostsByBrand(console_brand_id);
    }

    public List<GameSalePost> getAllPostsByConsole(int console_id) {
        Optional<Console> optionalConsole= consoleRepository.findConsoleByID(console_id);
        if (!optionalConsole.isPresent()){
            throw new IllegalStateException("Console does not exist");
        }
        return gameSalePostRepository.findAllPostsByConsole(console_id);
    }

    public List<GameSalePost> getAllPostsByGame(Long games_id) {
        Optional<Games> optionalGames= gamesRepository.findById(games_id);
        if (!optionalGames.isPresent()){
            throw new IllegalStateException("Games does not exist");
        }
        return gameSalePostRepository.findAllPostsByGames(games_id);
    }

    public List<GameSalePost> getAllPostByUser(Long user_id) {
        Optional<UserProfile> optionalUserProfile= userProfileRepository.findById(user_id);
        if (!optionalUserProfile.isPresent()){
            throw new IllegalStateException("User does not exits");
        }
        return gameSalePostRepository.findAllPostsByUser(user_id);
    }
    public List<GameSalePost> getTopFewPosts(int start_post_number, int last_post_number) {
        Pageable range =  PageRequest.of(start_post_number, last_post_number);
        return gameSalePostRepository.findGameSalePostByTimeStamp(range);
    }

    //Post

    public ResponseEntity addPosts(String jwt, GameSalePost gameSalePost) throws UnsupportedEncodingException {
        JwtUtil jwtToken = new JwtUtil();
        JSONObject jwtBody = jwtToken.decodeToken(jwt);
        Long userId = Long.valueOf((int) jwtBody.get("id"));
        Optional<Games> optionalGames = gamesRepository.findById(gameSalePost.getGames_ID());
        if (!optionalGames.isPresent()) {
            throw new IllegalStateException("Games does not exist");
        }
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);
        if (!userProfileOptional.isPresent()){
            throw  new IllegalStateException("User does not exist");
        }
        gameSalePost.setGames(optionalGames.get());
        gameSalePost.setUserProfile(userProfileOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(gameSalePostRepository.save(gameSalePost));
    }

    public GameSalePost editPosts(GameSalePost gameSalePost) {
        Long post_id =gameSalePost.getId();
        GameSalePost postOnDB = gameSalePostRepository.findById(post_id)
                .orElseThrow(()->new IllegalStateException(("the posts with id "+post_id+" does not exist")));
        Games gamesOnDB = gamesRepository.findById(gameSalePost.getGames_ID())
                .orElseThrow(()->new IllegalStateException(("games with id "+ gameSalePost.getGames_ID() +" does not exist")));
        UserProfile userOnDB = userProfileRepository.findById(gameSalePost.getUser_Id())
                .orElseThrow(()->new IllegalStateException(("user with id "+ gameSalePost.getUser_Id() +" does not exist")));
        postOnDB.setPrice(gameSalePost.getPrice());
        postOnDB.setPlace_for_transaction(gameSalePost.getPlace_for_transaction());
        postOnDB.setDescription(gameSalePost.getDescription());
        postOnDB.setContact_method(gameSalePost.getContact_method());
        postOnDB.setUserProfile(userOnDB);
        postOnDB.setGames(gamesOnDB);
        return postOnDB;


    }

    public String deletePosts(long posts_id) {
        Optional<GameSalePost> gameSalePostOptional= gameSalePostRepository.findById(posts_id);
        if (!gameSalePostOptional.isPresent()){
            throw  new IllegalStateException("the posts with id "+posts_id+" does not exist");
        }
        gameSalePostRepository.deleteById(posts_id);
        return ("Success deleting the post with id: "+posts_id);
    }


}
