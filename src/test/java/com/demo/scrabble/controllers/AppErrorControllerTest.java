package com.demo.scrabble.controllers;

import com.demo.scrabble.service.ScrabbleFinder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ScrabbleController.class, secure = false)
public class AppErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScrabbleFinder scrabbleFinder;

    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void shouldRespondWithServerErrorButNoContent() throws Exception {
        mockMvc.perform(get("/errors/error")).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldRespondWithServerError() throws Exception {
        mockMvc.perform(get("/error")).andExpect(status().is5xxServerError());
    }

}