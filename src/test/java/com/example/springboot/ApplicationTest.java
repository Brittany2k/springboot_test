package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing"));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL)).
                andExpect(content().string(containsString("testing")));
    }

    @Test
    void testHistory2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testingAgain"));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL)).
                andExpect(content().string(containsString("testingAgain")));
    }
}