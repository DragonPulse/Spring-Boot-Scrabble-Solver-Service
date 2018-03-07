package com.demo.scrabble.controllers;

import com.demo.scrabble.service.ScrabbleFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Vijay Kumar S
 * @descripton: Controller which is called for unhandled errors
 * Copyright (c) Demo VJ 2017
 * Created Date 10/14/2017
 */


@RestController
public class ScrabbleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScrabbleFinder scrabbleFinder;

    /**
     * Method to getScrabbleWords,
     * Will be called if the URI is coming as /words and additional data.
     *
     * @param word
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/words/{word}", method = RequestMethod.GET, produces = "application/json")
    public List<String> getScrabbleWords(@PathVariable String word) throws IOException {
        logger.info("Entering the Scrabble COntroller :: getScrabbleWords");
        return scrabbleFinder.getScrabbleWords(word);
    }

    /**
     * Method to redirect to swagger API by default.
     *
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/", produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        return new ModelAndView("/page/welcome");
    }
}
