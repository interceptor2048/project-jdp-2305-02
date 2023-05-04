package com.kodilla.ecommercee.controller;

import com.google.gson.Gson;
import com.kodilla.ecommercee.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsers_ReturnsHttp200() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void getExistingUser_ReturnsHttp200() throws Exception {
        UserDto stub = new UserDto(-1, "testUser", 1, 9999);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(result -> mockMvc.perform(get("/users/-1"))
                        .andExpect(status().isOk()));
    }

    @Test
    public void getNotExistingUser_ReturnsHttp404() throws Exception {
        mockMvc.perform(delete("/users/-2"))
                .andDo(result -> mockMvc.perform(get("/users/-2"))
                        .andExpect(status().isNotFound()));
    }

    @Test
    public void deleteExistingUser_ReturnsHttp200() throws Exception {
        UserDto stub = new UserDto(-3, "testUser", 1, 9999);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(result -> mockMvc.perform(delete("/users/-3"))
                        .andExpect(status().isOk()));
    }

    @Test
    public void deleteNotExistingUser_ReturnsHttp404() throws Exception {
        mockMvc.perform(delete("/users/-4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingUser_ReturnsHttp200() throws Exception {
        UserDto stub = new UserDto(-5, "testUser", 1, 9999);
        UserDto stub2 = new UserDto(-5, "testUserUpdated", 1, 1111);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        String json2 = gson.toJson(stub2);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(result -> mockMvc.perform(put("/users/updateUser/-5")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json2))
                        .andExpect(status().isOk()));
    }

    @Test
    public void updateNotExistingUser_ReturnsHttp404() throws Exception {
        UserDto stub = new UserDto(-6, "testUserUpdated", 1, 1111);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        mockMvc.perform(delete("/users/-6"))
                .andDo(result -> mockMvc.perform(put("/users/updateUser/-6")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                        .andExpect(status().isNotFound()));
    }

    @Test
    public void createUser_ReturnsHttp200() throws Exception {
        UserDto stub = new UserDto(-7, "testUser", 1, 9999);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void blockExistingUser_ReturnsHttp200() throws Exception {
        UserDto stub = new UserDto(-8, "testUser", 1, 9999);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(result -> mockMvc.perform(put("/users/blockUser/-8"))
                        .andExpect(status().isOk()));
    }

    @Test
    public void blockNotExistingUser_ReturnsHttp404() throws Exception {
        mockMvc.perform(delete("/users/-9"))
                .andDo(result -> mockMvc.perform(put("/users/blockUser/-9"))
                        .andExpect(status().isNotFound()));
    }

    @Test
    public void blockAlreadyBlockedUser_ReturnsHttp400() throws Exception {
        UserDto stub = new UserDto(-10, "testUser", 0, 9999);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(result -> mockMvc.perform(put("/users/blockUser/-10"))
                        .andExpect(status().isBadRequest()));
    }

    @Test
    public void generateKeyForExistingUser_ReturnsHttp200() throws Exception {
        UserDto stub = new UserDto(-11, "testUserUpdated", 1, 1111);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(result -> mockMvc.perform(put("/users/generateKey/-11"))
                        .andExpect(status().isOk()));
    }

    @Test
    public void generateKeyForNotExistingUser_ReturnsHttp404() throws Exception {
        mockMvc.perform(delete("/users/-12"))
                .andDo(result -> mockMvc.perform(put("/users/generateKey/-12"))
                        .andExpect(status().isNotFound()));
    }
}