package com.qsl.parser.lex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringReaderTest {

    @Test
    void nextToken_shouldCall_len_Times() {
        String testString = "This XZ a \n1234\n test string";
        StringReader underTest = new StringReader(testString);

        assertTokTyp(underTest, TokTyp.IDENT, "X01");
        assertTokTyp(underTest, TokTyp.WHITESPACE, "X02");
        assertTokTyp(underTest, TokTyp.IDENT, "X03");
        assertTokTyp(underTest, TokTyp.WHITESPACE, "X04");
        assertTokTyp(underTest, TokTyp.IDENT, "X05");
        assertTokTyp(underTest, TokTyp.WHITESPACE, "X06");
        assertTokTyp(underTest, TokTyp.NUMBER, "X07");
        assertTokTyp(underTest, TokTyp.WHITESPACE, "X08");
        assertTokTyp(underTest, TokTyp.IDENT, "X09");
        assertTokTyp(underTest, TokTyp.WHITESPACE, "X10");
        assertTokTyp(underTest, TokTyp.IDENT, "X11");
        assertTokTyp(underTest, TokTyp.EOF, "X12");
        assertTokTyp(underTest, TokTyp.EOF, "X13");

    }

    private static void assertTokTyp(StringReader underTest, TokTyp tokTyp, String msg) {
        Token token = underTest.nextToken();
        System.out.println(token);
        Assertions.assertEquals(tokTyp, token.toktyp(), msg);
    }

    @Test
    void pos_should_show_pos() {
        String testString = "abc\ndef";
        StringReader underTest = new StringReader(testString);

        underTest.nextToken();
        underTest.nextToken();
        underTest.nextToken();

        Pos expected = new Pos(7,2,3);
        assertEquals(expected, underTest.getPos());
    }
}