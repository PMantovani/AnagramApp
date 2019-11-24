package com.mantovani;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LetterFrequencyDictionary {
    private List<String> validWords;
    private HashMap<String, HashMap<Character, Integer>> frequencyMap;

    LetterFrequencyDictionary(List<String> words, String lettersFilter) {
        this.validWords = new ArrayList<>();
        this.frequencyMap = new HashMap<>();

        HashMap<Character, Integer> letterFilterFreq = new LetterFrequencyWord(lettersFilter).getFrequency();

        for (String word : words) {
            boolean shouldAddWord = true;
            HashMap<Character, Integer> dictionaryWordFreq = new LetterFrequencyWord(word).getFrequency();
            for (Map.Entry<Character, Integer> entry : dictionaryWordFreq.entrySet()) {
                if (entry.getValue() - letterFilterFreq.getOrDefault(entry.getKey(), 0) > 0) {
                    shouldAddWord = false;
                    break;
                }
            }

            if (shouldAddWord) {
                this.validWords.add(word);
                this.frequencyMap.put(word, dictionaryWordFreq);
            }
        }
    }

    HashMap<Character, Integer> getFrequency(String word) {
        return this.frequencyMap.getOrDefault(word, new HashMap<>());
    }

    List<String> getValidWords() {
        return this.validWords;
    }
}
