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
    void test1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=yesSir")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("yesSir")));
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=yesSir")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string((containsString(""))));
    }

    @Test
    void testcaseSens() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=yesMam")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete?post_text=yesmam")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string((containsString("yesMam"))));
    }


}