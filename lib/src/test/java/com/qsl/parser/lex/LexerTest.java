package com.qsl.parser.lex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LexerTest {

    @Test
    void nextToken() {
        String input = "var abc 12    \n bananas { } ! hello";
        Lexer underTest = new Lexer(input);
        checkToken(underTest, TokTyp.VAR, "Y01");
        checkToken(underTest, TokTyp.IDENT, "Y02");
        checkToken(underTest, TokTyp.NUMBER, "Y03");
        checkToken(underTest, TokTyp.IDENT, "Y04");
        checkToken(underTest, TokTyp.LBRACE, "Y05");
        checkToken(underTest, TokTyp.RBRACE, "Y06");
        checkToken(underTest, TokTyp.UNKNOWN, "Y07");
        checkToken(underTest, TokTyp.IDENT, "Y08");
        checkToken(underTest, TokTyp.EOF, "Y09");
        checkToken(underTest, TokTyp.EOF, "Y10");
        checkToken(underTest, TokTyp.EOF, "Y11");
        checkToken(underTest, TokTyp.EOF, "Y12");
    }

    private static void checkToken(Lexer underTest, TokTyp tokTyp, String msg) {
        Token peektoken = underTest.peekToken();
        Assertions.assertNotNull(peektoken);
        Assertions.assertEquals(tokTyp, peektoken.toktyp(), msg);
        Token token = underTest.nextToken();
        Assertions.assertNotNull(token);
        Assertions.assertEquals(tokTyp, token.toktyp(), msg);
    }

    @Test
    void peekToken() {
    }
}