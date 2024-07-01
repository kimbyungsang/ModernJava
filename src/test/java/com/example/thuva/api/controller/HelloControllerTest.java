package com.example.thuva.api.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;
//
//    @Test
//    public void resultHello() throws Exception {
//        String hello = "hello";
//
//        mvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(hello));
//
//    }
//
//    @Test
//    public void resultHelloDto() throws Exception {
//        String name = "hello";
//        int amount = 1000;
//
//        mvc.perform(get("/hello/dto")
//                .param("name", name)
//                .param("amount", String.valueOf(amount)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(name)))
//                .andExpect((jsonPath("$.amount", is(amount))));
//    }
}
