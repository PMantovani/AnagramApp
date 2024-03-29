package com.mantovani;

import java.util.*;

class AnagramProcessor {
    private List<String> validWords;
    private LetterFrequencyDictionary frequencyDictionary;
    private List<List<String>> allAnagrams;

    List<String> getAnagrams(List<String> allWords, String input) throws InputFormatException {
        if (!this.isValidInput(input)) {
            throw new InputFormatException("Input should contain only non-accentuated characters and whitespaces");
        }
        // Convert letters to uppercase and remove spaces
        input = input.toUpperCase().replace(" ", "");

        this.frequencyDictionary = new LetterFrequencyDictionary(allWords, input);
        this.validWords = this.frequencyDictionary.getValidWords();

        HashMap<Character, Integer> inputFrequency = new LetterFrequencyWord(input).getFrequency();
        this.allAnagrams = new ArrayList<>();
        this.getAnagramsForPendingChars(new ArrayList<>(),0, inputFrequency);
        return this.sortAnagrams(this.allAnagrams);
    }

    private boolean isValidInput(String input) {
        // Check if all characters are between A-Z uppercase or lowercase allowing whitespaces as well
        return input.matches("[a-zA-Z ]+");
    }

    private void getAnagramsForPendingChars(List<String> currentAnagram, int fromChar, HashMap<Character, Integer> pendingChars) {
        for (int i=fromChar; i<this.validWords.size(); i++) {
            String validWord = this.validWords.get(i);
            HashMap<Character, Integer> dictionaryFreqWord = this.frequencyDictionary.getFrequency(validWord);

            if (this.canAddWordToAnagram(dictionaryFreqWord, pendingChars)) {
                HashMap<Character, Integer> newFrequencyMap = this.subtractFrequency(pendingChars, dictionaryFreqWord);

                List<String> newAnagramWords = new ArrayList<>(currentAnagram);
                newAnagramWords.add(validWord);

                if (newFrequencyMap.isEmpty()) {
                    // Base case. This is an anagram, so add to anagram list
                    allAnagrams.add(newAnagramWords);
                } else {
                    // Recursive call. Go to next levels to find more anagrams.
                    this.getAnagramsForPendingChars(newAnagramWords, i+1, newFrequencyMap);
                }
            }
        }
    }

    private boolean canAddWordToAnagram(HashMap<Character, Integer> wordToAdd, HashMap<Character, Integer> pendingChars) {
        for (Map.Entry<Character, Integer> entry: wordToAdd.entrySet()) {
            int pendingFrequencyForChar = pendingChars.getOrDefault(entry.getKey(), 0);
            if (pendingFrequencyForChar - entry.getValue() < 0) {
                return false;
            }
        }
        return true;
    }

    private HashMap<Character, Integer> subtractFrequency(HashMap<Character, Integer> original, HashMap<Character, Integer> subtract) {
        HashMap<Character, Integer> result = new HashMap<>(original);

        for (Map.Entry<Character, Integer> entry: original.entrySet()) {
            int newFrequency = entry.getValue() - subtract.getOrDefault(entry.getKey(), 0);
            if (newFrequency == 0) {
                result.remove(entry.getKey());
            } else {
                result.put(entry.getKey(), newFrequency);
            }
        }
        return result;
    }

    private List<String> sortAnagrams(List<List<String>> unsortedAnagrams) {
        List<String> sortedAnagram = new ArrayList<>();
        for (List<String> anagramWords : unsortedAnagrams) {
            Collections.sort(anagramWords);
            sortedAnagram.add(String.join(" ", anagramWords));
        }
        return sortedAnagram;
    }
}
