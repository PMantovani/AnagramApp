package com.mantovani;

import java.util.HashMap;
import java.util.List;

class LetterFrequencyDictionary {
    private HashMap<String, HashMap<Character, Integer>> frequencyMap;

    LetterFrequencyDictionary(List<String> words) {
        frequencyMap = new HashMap<>();

        for (String word : words) {
            LetterFrequencyWord charFreq = new LetterFrequencyWord(word);
            frequencyMap.put(word, charFreq.getFrequency());
        }
    }

    HashMap<Character, Integer> getFrequency(String word) {
        return this.frequencyMap.getOrDefault(word, new HashMap<>());
    }
}
