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
            Arguments.of(TokTyp.IDENT,"a123_def"),
            Arguments.of(TokTyp.NUMBER,"12.34"),
            Arguments.of(TokTyp.STRING,"\"some string \""),
            Arguments.of(TokTyp.LBRACE, "{"),
            Arguments.of(TokTyp.RBRACE, "}"),
            Arguments.of(TokTyp.LBRACKET, "["),
            Arguments.of(TokTyp.RBRACKET, "]"),
            Arguments.of(TokTyp.LPAREN, "("),
            Arguments.of(TokTyp.RPAREN, ")"),
            Arguments.of(TokTyp.COMMA, ","),
            Arguments.of(TokTyp.DOT, "."),
            Arguments.of(TokTyp.SEMICOLON, ";"),
            Arguments.of(TokTyp.VAR, "var"),
            Arguments.of(TokTyp.EXEC, "exec"),
            Arguments.of(TokTyp.EOF, "EOF")
        );
    }

    @ParameterizedTest
    @MethodSource("allTokTypeArgs")
    void shouldParseAllStringsAsTrue(TokTyp tokTyp, String input) {
        Pattern pattern = Pattern.compile(tokTyp.getRegex());
        Matcher matcher = pattern.matcher(input);
        assertTrue(matcher.find());
    }
}