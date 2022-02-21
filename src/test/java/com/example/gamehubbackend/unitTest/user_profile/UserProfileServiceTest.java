package com.example.gamehubbackend.unitTest.user_profile;

import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import com.example.gamehubbackend.user_profile.UserProfileService;
import com.example.gamehubbackend.user_profile.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserProfileServiceTest {
    @Autowired
    private UserProfileService userProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @Test
    @DisplayName("Test fetching all user profile")
    void getAllUserProfile() {
        UserProfile userProfile1 = new UserProfile(
                "martin",
                "tsang",
                "martin.tsang@gmail.com",
                "password",
                UserRole.USER
        );
        UserProfile userProfile2 = new UserProfile(
                "chris",
                "lee",
                "chirs.lee@gmail.com",
                "password",
                UserRole.USER
        );
        doReturn(Arrays.asList(userProfile1,userProfile2)).when(userProfileRepository).findAll();

        List<UserProfile> response = userProfileService.getAllUserProfiles();
        Assertions.assertNotNull(response, "user profile should not be null");
        Assertions.assertEquals(2, response.size(), "findAll should return 2 user profiles");
    }

    @Test
    @DisplayName("Test creating one user profile")
    void createUserProfile() {
        UserProfile userProfile = new UserProfile(
                "martin",
                "tsang",
                "martin.tsang@gmail.com",
                "password",
                UserRole.USER
        );
        doReturn(userProfile).when(userProfileRepository).save(any());

        UserProfile response = userProfileService.createUserProfile(userProfile);
        Assertions.assertNotNull(response, "user profile should not be null");
        Assertions.assertEquals(userProfile, response, "user profile should be matched");
    }
}
