package com.skyfox83.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    /**
     *  TestController의 로직 테스트하는 코드 작성
     *  - 여러 테스트 패턴 중 "given-when-then' 패턴 적용
     *  - 참고) MockMvc는 어플리케이션을 서버 배포 없이 테스트용 MVC 환경을 만들어 요청/전송, 응답 기능 제공하는 유틸리티 클래스
     */
    @DisplayName("getAllMembers: 아티클 조회에 성공한다")
    @Test
    public void getAllMembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when
        final ResultActions result = mockMvc.perform(get(url)   // perform() 메서드는 '요청'을 전송하는 역할. 즉, url 요청을 만들고 그 return 값을 ResultActions에 담음
                        .accept(MediaType.APPLICATION_JSON));   // accept() 메서는 요청 보낼 때 '어떤 타입으로 받을지' 정하는 메서드

        // then
        result
                .andExpect(status().isOk())         // andExpect() 메서드는 응답을 검증. isOk() 메서드는 http 응답코드 중 200 ok 응답값 처리하는 메서드
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

}