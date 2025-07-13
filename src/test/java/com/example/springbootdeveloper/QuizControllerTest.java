package com.example.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //1. GET 요청을 보내 응답 코드마다 예상하는 응답을 반환하는지 검증하는 테스트 작성
    @DisplayName("quiz(): GET /quiz?code=1 이면 응답 코드는 201, 응답 본문은 Created!를 리턴")
    @Test
    public void getQuiz1() throws Exception {
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(get(url).param("code", "1"));

        result
                .andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }

    @DisplayName("quiz(): GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request!를 리턴")
    @Test
    public void getQuiz2() throws Exception {
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(get(url).param("code", "2"));

        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }

    //2. POST 요청을 보내 응답 코드마다 예상하는 응답을 반환하는지 검증하는 테스트 작성
    @DisplayName("quiz(): POST /quiz?code=1 이면 응답 코드는 403, 본문은 Forbidden!를 리턴")
    @Test
    public void postQuiz1() throws Exception {
        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Code(1))));

        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz(): POST /quiz?code=13 이면 응답 코드는 200, 응답 본문은 OK!를 리턴")
    @Test
    public void postQuiz13() throws Exception {
        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(13))));

        result
                .andExpect(status().isOk())
                .andExpect(content().string("OK!"));

    }
}