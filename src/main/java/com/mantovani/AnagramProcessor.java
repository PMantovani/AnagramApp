package com.mantovani;

import java.util.*;

class AnagramProcessor {
    private List<String> validWords;
    private List<String> allAnagrams;

    AnagramProcessor(List<String> validWords) {
        this.validWords = validWords;
    }

    List<String> getAnagrams(String input) throws InputFormatException {
        if (!this.isValidInput(input)) {
            throw new InputFormatException("Input should contain only non-accentuated characters and whitespaces");
        }

        HashMap<Character, Integer> inputFrequency = new LetterFrequencyWord(input.toUpperCase()).getFrequency();
        this.allAnagrams = new ArrayList<>();
        this.getAnagramsForPendingChars("",0, inputFrequency);
        return this.sortAnagrams(this.allAnagrams);
    }

    private boolean isValidInput(String input) {
        // Check if all characters are between A-Z uppercase or lowercase allowing whitespaces as well
        return input.matches("[a-zA-Z ]+");
    }

    private void getAnagramsForPendingChars(String currentAnagram, int fromChar, HashMap<Character, Integer> pendingChars) {
        for (int i=fromChar; i<validWords.size(); i++) {
            String validWord = validWords.get(i);

            if (this.canAddWordToAnagram(validWord, pendingChars)) {
                HashMap<Character, Integer> dictionaryFreqWord = new LetterFrequencyWord(validWord).getFrequency();
                HashMap<Character, Integer> newFrequencyMap = this.subtractFrequency(pendingChars, dictionaryFreqWord);

                String newAnagramWords;
                if (currentAnagram.equals("")) {
                    newAnagramWords = validWord;
                } else {
                    newAnagramWords = currentAnagram + " " + validWord;
                }

                if (newFrequencyMap.isEmpty()) {
                    // Base case. This is an anagram, so add to anagram list
                    allAnagrams.add(newAnagramWords);
                    return;
                } else {
                    // Recursive call. Go to next levels to find more anagrams.
                    this.getAnagramsForPendingChars(newAnagramWords, i+1, newFrequencyMap);
                }
            }
        }
    }

    private boolean canAddWordToAnagram(String wordToAdd, HashMap<Character, Integer> pendingChars) {
        HashMap<Character, Integer> wordToAddFrequency = new LetterFrequencyWord(wordToAdd).getFrequency();

        for (Map.Entry<Character, Integer> entry: wordToAddFrequency.entrySet()) {
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

    private List<String> sortAnagrams(List<String> unsortedAnagrams) {
        List<String> sortedAnagram = new ArrayList<>();

        for (String anagram : unsortedAnagrams) {
            String[] wordsInAnagram = anagram.split(" ");
            Arrays.sort(wordsInAnagram);

            sortedAnagram.add(String.join(" ", wordsInAnagram));
        }
        return sortedAnagram;
    }
}
