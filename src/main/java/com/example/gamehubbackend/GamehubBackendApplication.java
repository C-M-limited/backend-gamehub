package com.example.gamehubbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableCaching
public class GamehubBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(GamehubBackendApplication.class, args);
		System.out.println("Server start running at port 8080");
	}
	@GetMapping
	public String getMessage(){
		return "This is the GameHub server";
	}

}
