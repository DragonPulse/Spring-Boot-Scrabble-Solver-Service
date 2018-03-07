package com.demo.scrabble.service;

import com.demo.scrabble.utils.ScrabbleDictionary;
import com.demo.scrabble.utils.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vijay Kumar S
 * @descripton: Controller which is called for unhandled errors
 * Copyright (c) Demo VJ 2017
 * Created Date 10/14/2017
 */

@Service
public class ScrabbleFinder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    List<String> combinationList;


    private StringBuilder candidate;

    /**
     * Method getScrabbleWords
     *
     * @param inputFromRequest
     * @return
     * @throws IOException
     */
    public List<String> getScrabbleWords(String requestData) throws IOException {
        logger.debug("Entering into getScrabbleWords");
        String inputFromRequest = requestData.toLowerCase();
        ScrabbleDictionary dictionary = ScrabbleDictionary.getInstance();
        possibleCombinations(inputFromRequest);
        List<Word> resultWords = new ArrayList<>();
        for (String key : combinationList) {
            int keyWordScore = ScrabbleDictionary.getPoint(key);
            List<Word> scrabbleWords = new ArrayList<>(dictionary.getWordList(keyWordScore, key.length()));

            Iterator<Word> scrabbleIterator = scrabbleWords.iterator();
            Iterator<Word> iterator = scrabbleIterator;
            while (iterator.hasNext()) {
                Word word = iterator.next();
                if (!word.canConstructWithLetters(key))
                    iterator.remove();
            }
            resultWords.addAll(scrabbleWords);
        }
        resultWords.sort((o1, o2) -> {
            if (o1.getPoints() == o2.getPoints())
                return 0;
            if (o1.getPoints() > o2.getPoints())
                return -1;
            return 1;
        });
        List<String> scrabbleWordStrings = new ArrayList<>();
        for (Word word : resultWords)
            scrabbleWordStrings.add(word.getValue());

        logger.debug("Exiting From  getScrabbleWords ::");
        return scrabbleWordStrings;
    }

    /**
     * Method to generate all possible combination for input word
     *
     * @param input
     */
    private void possibleCombinations(String input) {
        logger.debug("Entering into possibleCombinations");
        combinationList = new ArrayList<>();
        candidate = new StringBuilder();
        possibleCombinations(input, 0);
    }

    /**
     * Find All Combinations of the Word
     *
     * @param input
     * @param start
     */
    private void possibleCombinations(String input, int start) {
        logger.debug("Entering PossibleComination with 2 param ::" + input + " Start:: " + start);
        for (int i = start; i < input.length(); ++i) {
            candidate.append(input.charAt(i));
            combinationList.add(candidate.toString());
            if (i < input.length()) {
                possibleCombinations(input, i + 1);
            }
            candidate.setLength(candidate.length() - 1);

        }
    }
}
