package com.mantovani;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnagramProcessorTest {

    @Test
    void testValidInputContainingWhitespacesDoesNotThrowException() {
        assertDoesNotThrow(() -> {
            AnagramProcessor p = new AnagramProcessor();
            p.getAnagrams(new ArrayList<>(), "test all string with space");
        });
    }

    @Test
    void testInvalidInputContainingDigitsThrowException() {
        assertThrows(InputFormatException.class, () -> {
            AnagramProcessor p = new AnagramProcessor();
            p.getAnagrams(new ArrayList<>(), "1234");
        });
    }

    @Test
    void testInvalidInputContainingAccentuatedCharactersThrowsException() {
        assertThrows(InputFormatException.class, () -> {
            AnagramProcessor p = new AnagramProcessor();
            p.getAnagrams(new ArrayList<>(), "áccentuated");
        });
    }

    @Test
    void testValidInputAcceptsUppercase() {
        assertDoesNotThrow(() -> {
            AnagramProcessor p = new AnagramProcessor();
            p.getAnagrams(new ArrayList<>(), "UPPERCASE");
        });
    }

    @Test
    void testReturnMultipleAnagrams() throws InputFormatException {
        String[] validWords = {"ELM", "HO", "OH", "REV", "OHM", "REVEL", "LEVER",
                                "HOVER", "HOLM", "VEER", "HELM", "OVER", "ROVE"};

        AnagramProcessor p = new AnagramProcessor();
        List<String> anagrams = p.getAnagrams(Arrays.asList(validWords), "vermelho");
        assertEquals(8, anagrams.size());
    }

    @Test
    void testReturnAnagramSorted() throws InputFormatException {
        String[] validWords = {"A", "B", "C", "D"};

        AnagramProcessor p = new AnagramProcessor();
        List<String> anagrams = p.getAnagrams(Arrays.asList(validWords), "DCBA");
        assertEquals("A B C D", anagrams.get(0));
    }

    @Test
    void testReturnNoAnagram() throws InputFormatException {
        String[] validWords = {"A", "B", "C", "D"};

        AnagramProcessor p = new AnagramProcessor();
        List<String> anagrams = p.getAnagrams(Arrays.asList(validWords), "DCBAA");
        assertEquals(0, anagrams.size());
    }
}