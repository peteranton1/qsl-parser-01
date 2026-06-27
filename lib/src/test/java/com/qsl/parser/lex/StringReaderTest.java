package com.qsl.parser.lex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringReaderTest {

    @Test
    void nextChar_shouldCall_len_Times() {
        String testString = """
            This is a\s
             \
            1234\s
             \r test string""";
        StringReader underTest = new StringReader(testString);

        char EOF = '\0';
        char currentChar = EOF;
        char nextChar = EOF;

        for (int i = 0; i < testString.length(); i++) {
            if(i < testString.length()-1) {
                nextChar = testString.charAt(i+1);
            } else {
                nextChar = EOF;
            }
            currentChar = testString.charAt(i);
            assertEquals(nextChar, underTest.peekChar());
            assertEquals(currentChar, underTest.nextChar());
        }
        assertEquals(EOF, underTest.peekChar());
        assertEquals(EOF, underTest.nextChar());
    }

    @Test
    void pos_should_show_pos() {
        String testString = "abc\ndef";
        StringReader underTest = new StringReader(testString);

        for (int i = 0; i < testString.length(); i++) {
            underTest.nextChar();
        }
        Pos expected = new Pos(7,2,3);
        assertEquals(expected, underTest.getPos());
    }
}