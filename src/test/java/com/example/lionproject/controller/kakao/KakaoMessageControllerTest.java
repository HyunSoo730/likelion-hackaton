package com.example.lionproject.controller.kakao;

import com.example.lionproject.domain.kakao.KakaoMessageRequest;
import com.example.lionproject.service.kakao.KakaoMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KakaoMessageController.class)
public class KakaoMessageControllerTest {
    @MockBean
    private KakaoMessageService messageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSendMessage() throws Exception {
        //given
        KakaoMessageRequest request = new KakaoMessageRequest();
        request.setToken("testToken");
        request.setMessage("testMessage");
        request.setWebUrl("testWebUrl");

        //mock 객체를 사용하여 messageService의 sendMessage 메소드가 동작 설정
        doNothing().when(messageService).sendMessage(any(KakaoMessageRequest.class));

        //when
        mockMvc.perform(post("/kakao/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());
    }

    //kakaoMessageRequest 객체를 json 형태로 변환
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
