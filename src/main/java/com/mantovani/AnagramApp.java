package com.mantovani;

import java.io.FileNotFoundException;
import java.util.List;

public class AnagramApp {

    public static void main(String[] args) {
        // We need the first argument in the command-line to be filled.
        if (args.length == 0) {
            System.err.println("Please pass an input string as an argument to the program.");
            return;
        }

        try {
            long startTime = System.currentTimeMillis();
            // DictionaryFileReader class expects 'palavras.txt' to be in the resources folder
            DictionaryFileReader dictionaryReader = new DictionaryFileReader("palavras.txt");
            List<String> allWords = dictionaryReader.read();

            AnagramProcessor processor = new AnagramProcessor();
            List<String> anagrams = processor.getAnagrams(allWords, args[0]);
            long endTime = System.currentTimeMillis();
            for (String anagram: anagrams) {
                System.out.println(anagram);
            }
            System.out.println("\nExecution Time: " + (endTime - startTime) + "ms");
            System.out.println("Input length: " + args[0].length());
            System.out.println("Words found: " + anagrams.size());
        } catch (FileNotFoundException|InputFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}