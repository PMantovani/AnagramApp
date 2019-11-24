package com.mantovani;

import java.util.HashMap;

class LetterFrequencyWord {
    private HashMap<Character, Integer> frequency;

    LetterFrequencyWord(String word) {
        this.frequency = new HashMap<>();

        for (int i=0; i<word.length(); i++) {
            int nextCount = this.frequency.getOrDefault(word.charAt(i), 0) + 1;
            this.frequency.put(word.charAt(i), nextCount);
        }
    }

    HashMap<Character, Integer> getFrequency() {
        return frequency;
    }
}
