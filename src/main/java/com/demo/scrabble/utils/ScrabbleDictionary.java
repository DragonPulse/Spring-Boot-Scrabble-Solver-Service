package com.demo.scrabble.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


/**
 * @author Vijay Kumar S
 * @file: ScrabbleDictionary.java
 * @descripton: Controller which is called for unhandled errors
 * Created Date 10/16/2017
 * Copyright (c) Demo VJ 2017
 */

public class ScrabbleDictionary {
    /**
     *
     */
    private static ScrabbleDictionary instance;
    private static Map<Integer, Map<Integer, List<Word>>> dictionary  = new HashMap<>();; // <score,<length,{words}>>

    /**
     *
     */
    private ScrabbleDictionary() {

    }

    /**
     * Method getPoint
     *
     * @param input
     * @return
     */
    public static int getPoint(String input) {
        int points = 0;
        for (char c : input.toCharArray())
            points += getPoint(c);
        return points;
    }

    /**
     * Method to get point for a character
     *
     * @param scrabbleChar
     * @return //to do: can be still optimised by opting Map or enum for better optimised code
     */
    public static int getPoint(char scrabbleChar) {
        switch (scrabbleChar) {
            case 'd':
            case 'D':
            case 'g':
            case 'G':
                return 2;
            case 'b':
            case 'B':
            case 'c':
            case 'C':
            case 'm':
            case 'M':
            case 'p':
            case 'P':
                return 3;
            case 'f':
            case 'F':
            case 'h':
            case 'H':
            case 'v':
            case 'V':
            case 'w':
            case 'W':
            case 'y':
            case 'Y':
                return 4;
            case 'k':
            case 'K':
                return 5;
            case 'j':
            case 'J':
            case 'x':
            case 'X':
                return 8;
            case 'q':
            case 'Q':
            case 'z':
            case 'Z':
                return 10;
            default:
                return 1;// A, E,I,L,N,O,R,S,T,U
        }
    }

    /**
     * get Instance method
     *
     * @return
     * @throws IOException
     */
    public static ScrabbleDictionary getInstance() throws IOException {
            synchronized (ScrabbleDictionary.class) {
                if (instance == null) {
                    instance = new ScrabbleDictionary();
                    instance.initDictionary();
                }
            }
        return instance;
    }

    /**
     * initialise the dictionary
     *
     * @throws IOException
     */
    private void initDictionary() throws IOException {
        BufferedReader br = loadDictionary();

        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            addToDictionary(strLine);
        }

        //Close the input stream
        br.close();
    }

    /**
     * method to add all source words to dictionary
     *
     * @param strLine
     */
    private void addToDictionary(String strLine) {
        Word word = new Word(strLine.toLowerCase());

        if (dictionary.containsKey(word.getPoints())) {
            Map<Integer, List<Word>> lengthMap = dictionary.get(word.getPoints());
            if (lengthMap.containsKey(word.getLength())) {
                List<Word> wordList = lengthMap.get(word.getLength());
                wordList.add(word);
            } else {
                List<Word> newList = new ArrayList<>();
                newList.add(word);
                lengthMap.put(word.getLength(), newList);
            }
        } else {
            List<Word> newList = new ArrayList<>();
            newList.add(word);
            Map<Integer, List<Word>> lengthMap = new HashMap<>();
            lengthMap.put(word.getLength(), newList);
            dictionary.put(word.getPoints(), lengthMap);
        }
    }

    /**
     * method to load dictionary
     *
     * @return
     * @throws IOException
     */
    private BufferedReader loadDictionary() throws IOException {
        Resource resource = new ClassPathResource("static/datasource");
        InputStream inputStream = resource.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * get words list
     *
     * @param score
     * @param length
     * @return
     */
    public List<Word> getWordList(int score, int length) {
        if (dictionary == null)
            return Collections.emptyList();
        Map<Integer, List<Word>> lengthMap = dictionary.get(score);
        if (lengthMap == null)
            return Collections.emptyList();
        List<Word> words = lengthMap.get(length);
        if (words == null)
            return Collections.emptyList();
        return Collections.unmodifiableList(words);
    }
}
