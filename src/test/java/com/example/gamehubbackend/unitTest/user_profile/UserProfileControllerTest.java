package com.example.gamehubbackend.unitTest.user_profile;

import static org.hamcrest.Matchers.*;

import com.example.gamehubbackend.user_profile.UserProfile;
import com.example.gamehubbackend.user_profile.UserProfileRepository;
import com.example.gamehubbackend.user_profile.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserProfileRepository userProfileRepository;

    UserProfile PROFILE_1 = new UserProfile(1L, "martin","tsang","martin.tsang@gmail.com","password", UserRole.USER);
    UserProfile PROFILE_2 = new UserProfile(2L, "chris","lee","chris.lee@gmail.com","password",UserRole.USER);
    UserProfile PROFILE_3 = new UserProfile(3L,"tom","wong","tom.wong@gmail.com","password",UserRole.USER);

    @Test
    public void getAllUserProfile_success() throws Exception {

        List<UserProfile> profiles = new ArrayList<>(Arrays.asList(PROFILE_1, PROFILE_2, PROFILE_3));

        Mockito.when(userProfileRepository.findAll()).thenReturn(profiles);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/user_profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].email", is("chris.lee@gmail.com")));
    }

    @Test
    public void getUserProfileById_success() throws Exception {
        Mockito.when(userProfileRepository.findUserProfileByID(PROFILE_1.getId())).thenReturn(java.util.Optional.ofNullable(PROFILE_1));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/user_profile/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email", is("martin.tsang@gmail.com")));
    }
}
