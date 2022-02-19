package com.example.gamehubbackend.config.jwt.refreshToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void addToken(String token,Long user_id){
        RefreshToken refreshToken = new RefreshToken(token,user_id);
        //one day after the token will expire
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()+24*60*60*1000);
        refreshToken.setExpiry_date(timestamp);
        refreshTokenRepository.save(refreshToken);
    }

    public boolean checkTokenIsExist(String token){
        Optional<RefreshToken>refreshTokenOptional = refreshTokenRepository.findByToken(token);
        if (!refreshTokenOptional.isPresent()) {
            return false;
        }
        return true;
    }
    @Transactional
    public boolean checkTokenIsNotExpiryAndExtend(String token){
        Optional<RefreshToken>refreshTokenOptional = refreshTokenRepository.findByToken(token);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp TimestampOnDB = refreshTokenOptional.get().getExpiry_date();
        //if token expiry, return not valid, else extend the token life
        if (currentTimestamp.after(TimestampOnDB)){
            return true;
        }else{
            refreshTokenOptional.get().setExpiry_date(new Timestamp(System.currentTimeMillis()+24*60*60*1000));
        }
        return false;
    }

    public boolean checkUserHaveToken(Long id){
        Optional<RefreshToken>refreshTokenOptional = refreshTokenRepository.findByUserId(id);
        //unique return false
        if (!refreshTokenOptional.isPresent()) {
            return false;
        }
        return true;
    }
    @Transactional
    public void deleteToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
