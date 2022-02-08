package com.example.gamehubbackend.user_profile;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user_profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping()
    public List<UserProfile> getAllUserProfile() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping("/{id}")
    public UserProfile getUserProfile(@PathVariable("id") Long id) {
        System.out.println(id);
        return userProfileService.getUserProfiles(id);
    }

    @PostMapping()
    public String createUserProfile(@RequestBody UserProfile userProfile) {
        userProfileService.createUserProfile(userProfile);
        return "user profile created successfully";
    }
}
