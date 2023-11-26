package com.skyfox83.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyfox83.springbootdeveloper.config.jwt.JwtFactory;
import com.skyfox83.springbootdeveloper.config.jwt.JwtProperties;
import com.skyfox83.springbootdeveloper.domain.RefreshToken;
import com.skyfox83.springbootdeveloper.domain.User;
import com.skyfox83.springbootdeveloper.dto.CreateAccessTokenRequest;
import com.skyfox83.springbootdeveloper.repository.RefreshTokenRepository;
import com.skyfox83.springbootdeveloper.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext context;
    @Autowired
    protected JwtProperties jwtProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        userRepository.deleteAll();
    }

    @DisplayName("createNewAccessToken(): 새로운 액세스 토큰을 발급한다.")
    @Test
    public void createNewAccessToken() throws Exception {
        // given
        final String url = "/api/token";

        // 테스트 데이터 - 사용자 정보
        User testUser = userRepository.save(
                User.builder()
                        .email("user@gmail.com")
                        .password("test")
                        .build()
        );

        // 테스트 데이터 - 리프레시 토큰
        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);

        // 리프레시 토큰을 사용자id 와 맵핑하여 저장
        refreshTokenRepository.save(new RefreshToken(testUser.getId(), refreshToken));

        // 리프레시 토큰을 토대로 액세스 토큰 발급
        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(refreshToken);
        final String requestBody = objectMapper.writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody)
        );

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }
}
