package com.demo.scrabble.utils;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordTest {

    private Word word;

    @Before
    public void init() {
        word = new Word("test");
    }

    @Test
    public void testGetValue() throws Exception {
        Assert.assertEquals("test", word.getValue());
    }

    @Test
    public void testGetPoints() throws Exception {
        Assert.assertEquals(4, word.getPoints());
    }

    @Test
    public void testGetLength() throws Exception {
        Assert.assertEquals(4, word.getLength());
    }

    @Test
    public void canConstructWithLetters() throws Exception {
        Assert.assertTrue(word.canConstructWithLetters("estt"));
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("test", word.toString());
    }


}