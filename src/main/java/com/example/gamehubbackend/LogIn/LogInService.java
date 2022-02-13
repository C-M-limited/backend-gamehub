package com.example.gamehubbackend.LogIn;

import com.example.gamehubbackend.jwt.JwtUtil;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class LogInService {

    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LogInService(UserProfileRepository userProfileRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public  ResponseEntity login(HashMap<String, String> user) {
        String email = user.get("email");
        String password = user.get("password");
        //  Check valid email
        Optional<UserProfile> userProfileOptional= userProfileRepository.findUserProfileByEmail(email);
        if(!userProfileOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Email or Password2");
        }
        UserProfile userOnDB = userProfileOptional.get();
        // Check valid password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.matches(password, userOnDB.getPassword());
        if (!encoder.matches(password, userOnDB.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Email or Password2");
//            throw new IllegalStateException("Wrong Email or Password2");
        }
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(userOnDB); // 取得token
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        token
                )
                .body("Hello "+userOnDB.getFirstName());
    }
}
