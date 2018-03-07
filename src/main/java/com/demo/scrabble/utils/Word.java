package com.demo.scrabble.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vijay Kumar S
 * @descripton:
 * Created Date 10/16/2017
 * Copyright (c) Demo VJ 2017
 */


public class Word {

    @JsonIgnore
    protected int points;
    @JsonIgnore
    protected int length;
    @JsonIgnore
    protected Set<Character> elements;
    private String value;

    /**
     * Constructor
     *
     * @param value
     */
    public Word(String value) {
        this.value = value.toLowerCase();

        points = ScrabbleDictionary.getPoint(value);
        length = value.length();

        elements = new HashSet<>();
        for (char c : value.toCharArray())
            elements.add(Character.valueOf(c));
    }

    /**
     * get value
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * get points
     *
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * @param key
     * @return
     */
    public boolean canConstructWithLetters(String key) {
        Set<Character> keySet = new HashSet<>();
        for (char c : key.toCharArray())
            keySet.add(c);

        return elements.containsAll(keySet) && keySet.containsAll(elements);
    }

    @Override
    public String toString() {
        return value;
    }
}
