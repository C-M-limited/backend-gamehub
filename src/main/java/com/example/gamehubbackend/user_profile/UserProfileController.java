package com.example.gamehubbackend.user_profile;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user_profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getAllUserProfile() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping("{user_id}")
    @ResponseBody
    public UserProfile getUserProfile(@RequestParam Long user_id) {
        return userProfileService.getUserProfiles(user_id);
    }

    @PostMapping
    public String createUserProfile(@RequestBody UserProfile userProfile) {
        userProfileService.createUserProfile(userProfile);
        return "user profile created successfully";
    }
}
