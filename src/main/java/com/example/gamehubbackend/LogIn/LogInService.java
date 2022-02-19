package com.example.gamehubbackend.LogIn;

import com.example.gamehubbackend.config.jwt.JwtUtil;
import com.example.gamehubbackend.config.jwt.refreshToken.RefreshTokenService;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LogInService {

    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public LogInService(UserProfileRepository userProfileRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RefreshTokenService refreshTokenService) {
        this.userProfileRepository = userProfileRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    public  ResponseEntity login(HashMap<String, String> user) {
        String email = user.get("email");
        String password = user.get("password");
        //  Check valid email
        Optional<UserProfile> userProfileOptional= userProfileRepository.findUserProfileByEmail(email);
        if(!userProfileOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Email or Password");
        }
        UserProfile userOnDB = userProfileOptional.get();
        // Check valid password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.matches(password, userOnDB.getPassword());
        if (!encoder.matches(password, userOnDB.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Email or Password");
        }
        // Check the user have token already or not (other user use the same account log in at the other device)
        Boolean notUnique = refreshTokenService.checkUserHaveToken(userOnDB.getId());
        //if yes delete the existing token
        if (notUnique){
            refreshTokenService.deleteTokenByUserId(userOnDB.getId());
        }
        //send the token to user
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(userOnDB); // 取得token
        String refreshToken = jwtUtil.generateRefreshToken(userOnDB);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("refreshToken", refreshToken);
        responseHeaders.set(HttpHeaders.AUTHORIZATION,token);
        //save the token
        refreshTokenService.addToken(refreshToken,userOnDB.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "Hello "+userOnDB.getFirstName());
        map.put("refreshToken", refreshToken);
        map.put("AUTHORIZATION", token);
        return new ResponseEntity<Object>(map, HttpStatus.valueOf(200));
//        return ResponseEntity.ok()
//                .headers(responseHeaders)
//                .body("Hello "+userOnDB.getFirstName());
    }

    public ResponseEntity logOut(String refreshToken) {
        refreshTokenService.deleteToken(refreshToken);
        return ResponseEntity.ok()
                .body("Success");
    }

    public ResponseEntity refresh(String rawJwt) throws UnsupportedEncodingException, AuthException {
//        String authorizationHeader = request.getHeader("Authorization");

        //check the token is exist
        if (rawJwt!= null && rawJwt.startsWith("Bearer")) {
            String refreshToken = rawJwt.substring("Bearer ".length());
            JwtUtil jwtUtil = new JwtUtil();
            try {
                //check the token is a token (created by us)
                jwtUtil.validateToken(refreshToken);
            } catch (AuthException e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            }
            //if user logout , this test won't pass
            Boolean isValid = refreshTokenService.checkTokenIsExist(refreshToken);
            if(!isValid){
                throw new IllegalStateException("Not a Valid Token");
            }
            //check the email really exist (make sure the content does not modify by someone)
            JSONObject jwtBody = jwtUtil.decodeToken(refreshToken);
            String user_email = String.valueOf(jwtBody.get("email"));
            Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByEmail(user_email);
            if (!userProfileOptional.isPresent()) {
                throw new IllegalStateException("Not a Valid Token");
            }
            //check the token does not expiry and extend it if it is valid
            Boolean isExpiry = refreshTokenService.checkTokenIsNotExpiryAndExtend(refreshToken);
            if(isExpiry){
                throw new AuthException("Expired JWT token");
            }
            //return a new token to user
            String accessToken = jwtUtil.generateToken(userProfileOptional.get());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(HttpHeaders.AUTHORIZATION, accessToken);
            responseHeaders.set("refreshToken", refreshToken);
            return ResponseEntity.ok()
                    .headers(responseHeaders).body("This is your new token set");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token is Missing");
    }

}
