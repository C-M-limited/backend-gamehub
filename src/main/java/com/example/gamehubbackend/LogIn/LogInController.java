package com.example.gamehubbackend.LogIn;

import com.example.gamehubbackend.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

