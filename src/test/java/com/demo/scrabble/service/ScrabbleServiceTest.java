package com.demo.scrabble.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

public class ScrabbleServiceTest {

    private ScrabbleFinder scrabbleService;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        scrabbleService = new ScrabbleFinder();
    }

    @Test
    public void testGetWordsReturnsAListOfWords() throws IOException {
        List words = scrabbleService.getScrabbleWords("hat");
        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 6);
    }

    @Test
    public void testGetWordsReturnsZeroWords() throws IOException {
        List words = scrabbleService.getScrabbleWords("zzz");
        Assert.assertTrue(words.isEmpty());
        Assert.assertEquals(words.size(), 0);
    }
}
