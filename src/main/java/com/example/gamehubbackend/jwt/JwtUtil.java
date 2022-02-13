package com.example.gamehubbackend.jwt;

import antlr.BaseAST;
import com.example.gamehubbackend.user_profile.UserProfile;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil implements Serializable {
    //15 mins expire
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;
    //6 months expire
    private static final long EXPIRATION_TIME_REFRESH_TOKEN =  24* 60 * 60 * 1000;
    /**
     * JWT SECRET KEY
     */
    private final String SECRET = "MyNameIsChris";

    /**
     * 簽發JWT
     */
    public String generateToken(UserProfile user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put( "id", user.getId());
        claims.put( "role", user.getRole() );
        claims.put( "email", user.getEmail() );
        return Jwts.builder()
                .setClaims( claims )
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME  ) )
                .signWith( SignatureAlgorithm.HS512, SECRET )
                .compact();
    }
    public String generateRefreshToken(UserProfile user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put( "email", user.getEmail() );
        return Jwts.builder()
                .setClaims( claims )
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME_REFRESH_TOKEN  ) )
                .signWith( SignatureAlgorithm.HS512, SECRET )
                .compact();
    }

    /**
     * 驗證JWT
     */
    public void validateToken(String token) throws AuthException {
        try {
            Jwts.parser()
                    .setSigningKey( SECRET )
                    .parseClaimsJws( token );
        } catch (SignatureException e) {
            throw new AuthException("Invalid JWT signature.");
        }
        catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token.");
        }
        catch (ExpiredJwtException e) {
            throw new AuthException("Expired JWT token");
        }
        catch (UnsupportedJwtException e) {
            throw new AuthException("Unsupported JWT token");
        }
        catch (IllegalArgumentException e) {
            throw new AuthException("JWT token compact of handler are invalid");
        }
    }
    //Decode Token
    public JSONObject decodeToken(String token) throws UnsupportedEncodingException {
        String[] pieces = token.split("\\.");
//        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = pieces[1];
//        String base64EncodedSignature = split_string[2];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject jsonObject = new JSONObject(body);
        return jsonObject;
    }

}
