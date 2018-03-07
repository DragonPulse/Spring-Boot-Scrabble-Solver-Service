package com.demo.scrabble.controllers;

import com.demo.scrabble.service.ScrabbleFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ScrabbleController.class, secure = false)
public class ScrabbleControllerTest {

    List<String> mockList = Arrays.asList("hat", "ah", "ha", "th", "at", "a");
    List<String> emptyMockList = Arrays.asList("");
    String expectedResponseForHAT = "[\"hat\",\"ah\",\"ha\",\"th\",\"at\",\"a\"]";
    String expectedResponseForZZZ = "[\"\"]";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScrabbleFinder scrabbleFinder;


    @Test
    public void retrieveDetailsForValid() throws Exception {
        Mockito.when(
                scrabbleFinder.getScrabbleWords(
                        Mockito.anyString())).thenReturn(mockList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/words/hat").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(expectedResponseForHAT, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void retrieveDetailsForInValid() throws Exception {
        Mockito.when(
                scrabbleFinder.getScrabbleWords(
                        Mockito.anyString())).thenReturn(emptyMockList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/words/zzz").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(expectedResponseForZZZ, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void retrieveDetailsForBalnk() throws Exception {
        Mockito.when(
                scrabbleFinder.getScrabbleWords(
                        Mockito.anyString())).thenReturn(mockList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/words/hat").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(expectedResponseForHAT, result.getResponse()
                .getContentAsString(), false);
    }

}