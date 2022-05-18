package com.project.freelance.controller.auth.singup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.freelance.config.SecurityConfigTest;
import com.project.freelance.constant.ApiConstant;
import com.project.freelance.model.UserEntity;
import com.project.freelance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@Transactional
@SpringBootTest(classes = SecurityConfigTest.class)
@AutoConfigureMockMvc
public class AuthSingUpControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void givenValidRequest_whenCreateUser_shouldReturnValidResponse() throws Exception {
        mockCallAndExpectAuthSingUp();
    }

    private void mockCallAndExpectAuthSingUp() throws Exception {
        SignUpRequest request = new SignUpRequest().builder()
                .userName("denidwik")
                .email("denidwikk@gmail.com")
                .password("password123")
                .confirmPassword("password123")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/" + ApiConstant.SIGNUP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Long response = objectMapper.readValue(content, Long.class);

        List<UserEntity> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUserName()).isEqualTo("denidwik");

    }

}
