package com.example.gamehubbackend.user_profile;

import com.example.gamehubbackend.registration.token.ConfirmationToken;
import com.example.gamehubbackend.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserProfileService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

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
    public UserProfile createUserProfile(UserProfile userProfile) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByEmail(userProfile.getUsername());
        if (userProfileOptional.isPresent()) {
            throw new IllegalStateException("the user profile already exist");
        }
        return userProfileRepository.save(userProfile);
    }

    // PUT
    public void updateUserProfile(UserProfile userProfile) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByEmail(userProfile.getUsername());
        if (userProfileOptional.isPresent()) {
            throw new IllegalStateException("the user profile already exist");
        }
    }

    // DELETE
    public void deleteUserProfile(UserProfile userProfile) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findUserProfileByEmail(userProfile.getUsername());
        if (userProfileOptional.isEmpty()) {
            throw new IllegalStateException("the user profile does not exist");
        }
    }

    // SECURITY
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userProfileRepository.findUserProfileByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(UserProfile userProfile) {
        boolean userExist = userProfileRepository
                .findUserProfileByEmail(userProfile.getEmail())
                .isPresent();
        if (userExist) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(userProfile.getPassword());

        userProfile.setPassword(encodedPassword);

        userProfileRepository.save(userProfile);

        String token = UUID.randomUUID().toString();
        // TODO: Send confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userProfile
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: SEND EMAIL

        return token;
    }

    public int enableUserProfile(String email) {
        return userProfileRepository.enableUserProfile(email);
    }
}
