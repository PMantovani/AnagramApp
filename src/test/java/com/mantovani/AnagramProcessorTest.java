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
            AnagramProcessor p = new AnagramProcessor(new ArrayList<>());
            p.getAnagrams("test all string with space");
        });
    }

    @Test
    void testInvalidInputContainingDigitsThrowException() {
        assertThrows(InputFormatException.class, () -> {
            AnagramProcessor p = new AnagramProcessor(new ArrayList<>());
            p.getAnagrams("1234");
        });
    }

    @Test
    void testInvalidInputContainingAccentuatedCharactersThrowsException() {
        assertThrows(InputFormatException.class, () -> {
            AnagramProcessor p = new AnagramProcessor(new ArrayList<>());
            p.getAnagrams("áccentuated");
        });
    }

    @Test
    void testValidInputAcceptsUppercase() {
        assertDoesNotThrow(() -> {
            AnagramProcessor p = new AnagramProcessor(new ArrayList<>());
            p.getAnagrams("UPPERCASE");
        });
    }

    @Test
    void testReturnMultipleAnagrams() throws InputFormatException {
        String[] validWords = {"ELM", "HO", "OH", "REV", "OHM", "REVEL", "LEVER",
                                "HOVER", "HOLM", "VEER", "HELM", "OVER", "ROVE"};

        AnagramProcessor p = new AnagramProcessor(Arrays.asList(validWords));
        List<String> anagrams = p.getAnagrams("vermelho");
        assertEquals(8, anagrams.size());
    }

    @Test
    void testReturnAnagramSorted() throws InputFormatException {
        String[] validWords = {"A", "B", "C", "D"};

        AnagramProcessor p = new AnagramProcessor(Arrays.asList(validWords));
        List<String> anagrams = p.getAnagrams("DCBA");
        assertEquals("A B C D", anagrams.get(0));
    }

    @Test
    void testReturnNoAnagram() throws InputFormatException {
        String[] validWords = {"A", "B", "C", "D"};

        AnagramProcessor p = new AnagramProcessor(Arrays.asList(validWords));
        List<String> anagrams = p.getAnagrams("DCBAA");
        assertEquals(0, anagrams.size());
    }
}