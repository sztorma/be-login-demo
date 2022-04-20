package com.sztorma.logindemo.route.controller;

import java.util.HashMap;

import com.sztorma.logindemo.security.JwtTokenUtil;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    @DisplayName("Getting admin routes successfully")
    public void testGetRoutesForAdminRolesSuccess() throws Exception {
        final String jwt = jwtTokenUtil.doGenerateToken(new HashMap<>(), "Admin",
                System.currentTimeMillis(),
                5 * 60 * 60);
        mockRequest(jwt) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(3))) //
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("admin")) //
                .andExpect(MockMvcResultMatchers.jsonPath("[0].link").value("/menu/admin"));
    }

    @Test
    @DisplayName("Getting moderator routes successfully")
    public void testGetRoutesForModeratorRolesSuccess() throws Exception {
        final String jwt = jwtTokenUtil.doGenerateToken(new HashMap<>(), "User 1",
                System.currentTimeMillis(),
                5 * 60 * 60);
        mockRequest(jwt) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(2))) //
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("moderator")) //
                .andExpect(MockMvcResultMatchers.jsonPath("[0].link").value("/menu/moderator"));
    }

    @Test
    @DisplayName("Getting user routes successfully")
    public void testGetRoutesForUserRolesSuccess() throws Exception {
        final String jwt = jwtTokenUtil.doGenerateToken(new HashMap<>(), "User 2",
                System.currentTimeMillis(),
                5 * 60 * 60);
        mockRequest(jwt) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(1))) //
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("user")) //
                .andExpect(MockMvcResultMatchers.jsonPath("[0].link").value("/menu/user"));
    }

    private ResultActions mockRequest(String jwt) throws Exception {
        return this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/route/get/for-current-roles/for-component/menu-nav") //
                        .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwt));
    }

}
