package com.mantovani;

import java.io.FileNotFoundException;
import java.util.List;

public class AnagramApp {

    public static void main(String[] args) {
        try {
            // DictionaryFileReader class expects 'palavras.txt' to be in the resources folder
            DictionaryFileReader dictionaryReader = new DictionaryFileReader("palavras.txt");
            List<String> validWords = dictionaryReader.read();

            AnagramProcessor processor = new AnagramProcessor(validWords);
            List<String> anagrams = processor.getAnagrams(args[1]);
            for (String anagram: anagrams) {
                System.out.println(anagram);
            }
        } catch (FileNotFoundException|InputFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}