package com.qsl.parser.lex;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class TokTypTest {

    public static Stream<Arguments> allTokTypeArgs() {
        return Stream.of(
            Arguments.of(TokTyp.ANS,
                "ans", "ans 12\n    34.2"),
            Arguments.of(TokTyp.CHAR,
                "char", "char 12\n    34.2"),
            Arguments.of(TokTyp.COMP,
                "comp", "comp 12\n    34.2"),
            Arguments.of(TokTyp.EXEC,
                "exec", "exec 12\n    34.2"),
            Arguments.of(TokTyp.QT,
                "qt", "qt 12\n    34.2"),
            Arguments.of(TokTyp.VAR,
                "var", "var 12\n    34.2"),
            Arguments.of(TokTyp.IDENT,
                "a123_def","a123_def {12\n    34.2"),
            Arguments.of(TokTyp.IDENT,
                "a","a "),
            Arguments.of(TokTyp.NUMBER,
                "12.34","12.34 12\n    34.2"),
            Arguments.of(TokTyp.NUMBER,
                "+12","+12 .34 12\n    34.2"),
            Arguments.of(TokTyp.STRING,
                "\"some string \"","\"some string \" 12\n    34.2"),
            Arguments.of(TokTyp.LBRACE,
                "{", "{ 12\n    34.2"),
            Arguments.of(TokTyp.RBRACE,
                "}", "} 12\n    34.2"),
            Arguments.of(TokTyp.LBRACKET,
                "[", "[ 12\n    34.2"),
            Arguments.of(TokTyp.RBRACKET,
                "]", "] 12\n    34.2"),
            Arguments.of(TokTyp.LPAREN,
                "(", "( 12\n    34.2"),
            Arguments.of(TokTyp.RPAREN,
                ")", ") 12\n    34.2"),
            Arguments.of(TokTyp.COMMA,
                ",", ", 12\n    34.2"),
            Arguments.of(TokTyp.DOT,
                ".", ". 12\n    34.2"),
            Arguments.of(TokTyp.SUM_MULT,
                "*", "* 12\n    34.2"),
            Arguments.of(TokTyp.SUM_DIV,
                "/", "/ 12\n    34.2"),
            Arguments.of(TokTyp.SUM_PLUS,
                "+", "+ 12\n    34.2"),
            Arguments.of(TokTyp.SUM_MINUS,
                "-", "- 12\n    34.2"),
            Arguments.of(TokTyp.SEMICOLON,
                ";", "; 12\n    34.2"),
            Arguments.of(TokTyp.WHITESPACE,
                "    \n", "    \n12\n    34.2")
        );
    }

    @ParameterizedTest
    @MethodSource("allTokTypeArgs")
    void shouldParseAllStringsAsTrue(TokTyp tokTyp, String expected, String input) {
        Pattern pattern = tokTyp.getPattern();
        Matcher matcher = pattern.matcher(input);
        assertTrue(matcher.find(),
            "Unmatched string: " + input +
                ", pattern " + pattern);
        System.out.println("  - Found \"" + matcher.group() +
            "\" at position: " + matcher.start());
        assertEquals(0, matcher.start());
        assertEquals(expected, matcher.group());
    }
}