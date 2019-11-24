package com.mantovani;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

class LetterFrequencyWordTest {

    @Test
    void testFrequencyIsCorrectMultipleLetters() {
        LetterFrequencyWord cut = new LetterFrequencyWord("abbccc");

        HashMap<Character, Integer> charFrequency = cut.getFrequency();
        assertEquals(charFrequency.get('a'), 1);
        assertEquals(charFrequency.get('b'), 2);
        assertEquals(charFrequency.get('c'), 3);
    }

}
