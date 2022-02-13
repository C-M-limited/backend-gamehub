package com.example.gamehubbackend.jwt.refreshToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void addToken(String token){
        RefreshToken refreshToken = new RefreshToken(token);
        refreshTokenRepository.save(refreshToken);
    }

    public boolean checkTokenExist(String token){
        Optional<RefreshToken>refreshTokenOptional = refreshTokenRepository.findByToken(token);
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
