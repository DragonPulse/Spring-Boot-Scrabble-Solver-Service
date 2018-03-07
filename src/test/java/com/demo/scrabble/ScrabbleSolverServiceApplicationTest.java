package com.demo.scrabble;

import com.demo.scrabble.service.ScrabbleFinder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScrabbleSolverServiceApplicationTest {

    @Autowired
    private ScrabbleFinder service;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testApplicationStartsUp() {
        Assert.assertNotNull(service);
    }

}