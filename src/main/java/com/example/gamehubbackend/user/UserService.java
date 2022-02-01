package com.example.gamehubbackend.user;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class UserService {
    public List<User> getAllUsers() {
        return List.of(
                new User(
                        1L,
                        1,
                        "martin",
                        "martin@gmail.com",
                        "hashpw",
                        true,
                        Timestamp.from(Instant.now()),
                        Timestamp.from(Instant.now()),
                        Timestamp.from(Instant.now())
                )
        );
    }
}
