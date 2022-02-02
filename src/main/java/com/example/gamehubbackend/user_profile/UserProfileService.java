package com.example.gamehubbackend.user_profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    // GET
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserProfiles(Long user_id) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByID(user_id);
        if (userProfileOptional.isEmpty()) {
            throw new IllegalStateException("the user profile does not exist");
        }
        return userProfileRepository.findUserProfileByID(user_id).get();
    }

    // POST
    public void createUserProfile(UserProfile userProfile) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByName(userProfile.getUser_name());
        if (userProfileOptional.isPresent()) {
            throw new IllegalStateException("the user profile already exist");
        }
        userProfileRepository.save(userProfile);
    }

    // PUT
    public void updateUserProfile(UserProfile userProfile) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByID(userProfile.getUser_id());
        if (userProfileOptional.isPresent()) {
            throw new IllegalStateException("the user profile already exist");
        }
    }

    // DELETE
    public void deleteUserProfile(UserProfile userProfile) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByID(userProfile.getUser_id());
        if (userProfileOptional.isEmpty()) {
            throw new IllegalStateException("the user profile does not exist");
        }
    }
}
