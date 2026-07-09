package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.tree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParseSumExprTest {

    public static Stream<Arguments> sumExprArgs() {
        return Stream.of(
            Arguments.of("1", "[NUMBER:1]"),
            Arguments.of("\"a\"", "[STRING:\"a\"]"),
            Arguments.of("q1", "['[IDENT:q1]'=[null]]"),
            Arguments.of("q2(2)", "['[IDENT:q2]'=[[NUMBER:2]]]"),
            Arguments.of("1 + 1", "[NUMBER:1]"),
            Arguments.of("2 - 2", "[NUMBER:2]"),
            Arguments.of("3 * 2", "[NUMBER:3]"),
            Arguments.of("4 / 4", "[NUMBER:4]"),
            Arguments.of("(1)", "[NUMBER:1]"),
            Arguments.of("(\"a\")", "[STRING:\"a\"]"),
            Arguments.of("(q1)", "['[IDENT:q1]'=[null]]"),
            Arguments.of("(q2(2))", "['[IDENT:q2]'=[[NUMBER:2]]]"),
            Arguments.of("(1 + 1)", "[NUMBER:1]"),
            Arguments.of("(2 - 2)", "[NUMBER:2]"),
            Arguments.of("(3 * 3)", "[NUMBER:3]"),
            Arguments.of("(4 / 4)", "[NUMBER:4]")
            );
    }

    @ParameterizedTest
    @MethodSource("sumExprArgs")
    void sumExpr(String input, String expected) {
        Lexer lexer = new Lexer(input);
        ParseSumExpr underTest = new ParseSumExpr(lexer, null);
        TreeNode actual = underTest.sumExpr(null);
        Assertions.assertEquals(expected, actual.toString());
    }
}