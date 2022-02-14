package com.example.gamehubbackend.LogIn;

import com.example.gamehubbackend.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1")
public class LogInController {
    private final LogInService logInService;

    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody HashMap<String, String> user){
        return logInService.login(user);
    }
    @PostMapping("/logOut")
    public ResponseEntity logOut(@RequestHeader("refreshToken") String refreshToken) {return logInService.logOut(refreshToken);}
    @PostMapping ("/token/refresh")
    public ResponseEntity refreshToken(@RequestHeader("refreshToken") String refreshToken) throws UnsupportedEncodingException, AuthException {
        return logInService.refresh(refreshToken);
    }
}

