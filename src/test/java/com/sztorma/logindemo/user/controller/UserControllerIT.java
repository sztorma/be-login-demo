package com.sztorma.logindemo.user.controller;

import java.util.HashMap;

import com.sztorma.logindemo.security.JwtTokenUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testGetUserFromTokenSuccess() throws Exception {
        final String token = jwtTokenUtil.doGenerateToken(new HashMap<>(), "Admin",
                System.currentTimeMillis(),
                5 * 60 * 60);
        ;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user/get/jwt")
                .contentType(MediaType.APPLICATION_JSON) //
                .header("Authorization", "Bearer " + token)) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Admin")); //
    }

}
