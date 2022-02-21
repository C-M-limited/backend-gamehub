package com.example.gamehubbackend.integrationTest.console_brand;

import com.example.gamehubbackend.GamehubBackendApplication;
import com.example.gamehubbackend.integrationTest.testcontainers.config.ContainersEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DirtiesContext
@SpringBootTest(classes = GamehubBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class consoleBrandControllerTest extends ContainersEnvironment {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @AfterEach
//    void tearDown()  {
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "console_brand");
//    }
    @Test
    @Transactional
    public void addConsoleBrand() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> body = new HashMap<>();
        body.put("name","Xbox");
//        body.put("team",Map.of("teamId",1));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/console_brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Xbox")));
    }
    @Test
    @Transactional
    public void getAllConsoleBrand() throws Exception {
        addConsoleBrand();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/console_brand/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Xbox")));
    }
//    @Test
//    @Transactional
//    public void editConsoleBrand() throws Exception {
//        addConsoleBrand();
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println("Happy");
//        Map<String,Object> body2 = new HashMap<>();
//        body2.put("id","3");
//        body2.put("name","PS");
//        mockMvc.perform(MockMvcRequestBuilders
//                .put("/api/v1/console_brand/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(body2))
//        )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(3)))
//                .andExpect(jsonPath("$.name", is("PS")));
//
//    }
//    @Test
//    public void deleteConsoleBrand() throws Exception {
//        addConsoleBrand();
//        System.out.println("Happy");
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String,Object> body2 = new HashMap<>();
//        body2.put("id","1");
//        body2.put("name","PS");
//        mockMvc.perform(MockMvcRequestBuilders
//                        .delete("/api/v1/console_brand/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(body2))
//                )
//                .andExpect(status().isOk());
//    }
}
