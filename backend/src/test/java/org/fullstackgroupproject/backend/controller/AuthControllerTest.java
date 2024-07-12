package org.fullstackgroupproject.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getMe_statusUnauthorized_withNoLoggedInUser() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void getMe_returnsString_withLoggedInUser() throws Exception {
        mockMvc.perform(get("/api/auth/me")
                .with(oidcLogin()
                        .userInfoToken(token -> token
                                //.claim("login"... gibt user von github zurück. Value muss "user" sein.
                                .claim("login", "user")
                                .claim("id", "123"))))
                .andExpect(status().isOk())
                .andExpect(content().string("user"));

    }
}