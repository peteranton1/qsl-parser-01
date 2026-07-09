package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.tree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ParseSumExprTest {

    public static Stream<Arguments> sumExprArgs1() {
        return Stream.of(
            Arguments.of("1", "[NUMBER:1]"),
            Arguments.of("\"a\"", "[STRING:\"a\"]"),
            Arguments.of("q1", "['[IDENT:q1]'=[null]]"),
            Arguments.of("q2(2)", "['[IDENT:q2]'=[[NUMBER:2]]]"),
            Arguments.of("1 + 11", "['[SUM_PLUS:+]'=[[NUMBER:1], [NUMBER:11]]]"),
            Arguments.of("2 - 12", "['[SUM_MINUS:-]'=[[NUMBER:2], [NUMBER:12]]]"),
            Arguments.of("3 * 13", "['[SUM_MULT:*]'=[[NUMBER:3], [NUMBER:13]]]"),
            Arguments.of("4 / 14", "['[SUM_DIV:/]'=[[NUMBER:4], [NUMBER:14]]]"),
            Arguments.of("(1)", "[NUMBER:1]"),
            Arguments.of("(\"a\")", "[STRING:\"a\"]"),
            Arguments.of("(q1)", "['[IDENT:q1]'=[null]]"),
            Arguments.of("(q2(2))", "['[IDENT:q2]'=[[NUMBER:2]]]"),
            Arguments.of("(1 + 11)", "['[SUM_PLUS:+]'=[[NUMBER:1], [NUMBER:11]]]"),
            Arguments.of("(2 - 12)", "['[SUM_MINUS:-]'=[[NUMBER:2], [NUMBER:12]]]"),
            Arguments.of("(3 * 13)", "['[SUM_MULT:*]'=[[NUMBER:3], [NUMBER:13]]]"),
            Arguments.of("(4 / 14)", "['[SUM_DIV:/]'=[[NUMBER:4], [NUMBER:14]]]"),
            Arguments.of("99.99", "[NUMBER:99.99]")
            );
    }

    public static Stream<Arguments> sumExprArgs2() {
        return Stream.of(
            Arguments.of("5 + 1 + 11", "['[SUM_PLUS:+]'" +
                "=[[NUMBER:5], " +
                "['[SUM_PLUS:+]'=[[NUMBER:1], [NUMBER:11]]]]]"),
            Arguments.of("5 - 2 - 12", "['[SUM_MINUS:-]'" +
                "=[[NUMBER:5], " +
                "['[SUM_MINUS:-]'=[[NUMBER:2], [NUMBER:12]]]]]"),
            Arguments.of("5 * 3 * 13", "['[SUM_MULT:*]'" +
                "=[[NUMBER:5], " +
                "['[SUM_MULT:*]'=[[NUMBER:3], [NUMBER:13]]]]]"),
            Arguments.of("5 / 4 / 14", "['[SUM_DIV:/]'" +
                "=[[NUMBER:5], " +
                "['[SUM_DIV:/]'=[[NUMBER:4], [NUMBER:14]]]]]"),
            Arguments.of("5 + (1 + 11)", "['[SUM_PLUS:+]'" +
                "=[[NUMBER:5], " +
                "['[SUM_PLUS:+]'=[[NUMBER:1], [NUMBER:11]]]]]"),
            Arguments.of("5 - (2 - 12)", "['[SUM_MINUS:-]'" +
                "=[[NUMBER:5], " +
                "['[SUM_MINUS:-]'=[[NUMBER:2], [NUMBER:12]]]]]"),
            Arguments.of("5 * (3 * 13)", "['[SUM_MULT:*]'" +
                "=[[NUMBER:5], " +
                "['[SUM_MULT:*]'=[[NUMBER:3], [NUMBER:13]]]]]"),
            Arguments.of("5 / (4 / 14)", "['[SUM_DIV:/]'" +
                "=[[NUMBER:5], " +
                "['[SUM_DIV:/]'=[[NUMBER:4], [NUMBER:14]]]]]"),
            Arguments.of("(5 + 1) + 11", "['[SUM_PLUS:+]'" +
                "=[['[SUM_PLUS:+]'=[[NUMBER:5], [NUMBER:1]]], " +
                "[NUMBER:11]]]"),
            Arguments.of("(5 - 2) - 12", "['[SUM_MINUS:-]'" +
                "=[['[SUM_MINUS:-]'=[[NUMBER:5], [NUMBER:2]]], " +
                "[NUMBER:12]]]"),
            Arguments.of("(5 * 3) * 13", "['[SUM_MULT:*]'" +
                "=[['[SUM_MULT:*]'=[[NUMBER:5], [NUMBER:3]]], " +
                "[NUMBER:13]]]"),
            Arguments.of("(5 / 4) / 14", "['[SUM_DIV:/]'" +
                "=[['[SUM_DIV:/]'=[[NUMBER:5], [NUMBER:4]]], " +
                "[NUMBER:14]]]"),
            Arguments.of("99.99", "[NUMBER:99.99]")
        );
    }

    public static Stream<Arguments> sumExprArgs3() {
        return Stream.of(
            Arguments.of("5 * 1 + 11", "['[SUM_PLUS:+]'" +
                "=[['[SUM_MULT:*]'=[[NUMBER:5], [NUMBER:1]]], [NUMBER:11]]]"),
            Arguments.of("5 / 2 - 12", "['[SUM_MINUS:-]'" +
                "=[['[SUM_DIV:/]'=[[NUMBER:5], [NUMBER:2]]], [NUMBER:12]]]"),
            Arguments.of("5 + 3 * 13", "['[SUM_PLUS:+]'" +
                "=[[NUMBER:5], ['[SUM_MULT:*]'=[[NUMBER:3], [NUMBER:13]]]]]"),
            Arguments.of("5 - 4 / 14", "['[SUM_MINUS:-]'" +
                "=[[NUMBER:5], ['[SUM_DIV:/]'=[[NUMBER:4], [NUMBER:14]]]]]"),
            Arguments.of("5 * (1 + 11)", "['[SUM_MULT:*]'" +
                "=[[NUMBER:5], ['[SUM_PLUS:+]'=[[NUMBER:1], [NUMBER:11]]]]]"),
            Arguments.of("5 / (2 - 12)", "['[SUM_DIV:/]'" +
                "=[[NUMBER:5], ['[SUM_MINUS:-]'=[[NUMBER:2], [NUMBER:12]]]]]"),
            Arguments.of("(5 + 3) * 13", "['[SUM_MULT:*]'" +
                "=[['[SUM_PLUS:+]'=[[NUMBER:5], [NUMBER:3]]], [NUMBER:13]]]"),
            Arguments.of("(5 - 4) / 14", "['[SUM_DIV:/]'" +
                "=[['[SUM_MINUS:-]'=[[NUMBER:5], [NUMBER:4]]], [NUMBER:14]]]"),
            Arguments.of("99.99", "[NUMBER:99.99]")
        );
    }

    @ParameterizedTest
    @MethodSource("sumExprArgs1")
    void sumExpr1(String input, String expected) {
        test1(input, expected);
    }

    @ParameterizedTest
    @MethodSource("sumExprArgs2")
    void sumExpr2(String input, String expected) {
        test1(input, expected);
    }

    @ParameterizedTest
    @MethodSource("sumExprArgs3")
    void sumExpr3(String input, String expected) {
        test1(input, expected);
    }

    private static void test1(String input, String expected) {
        Lexer lexer = new Lexer(input);
        ParseSumExpr underTest = new ParseSumExpr(lexer, null);
        TreeNode actual = underTest.sumExpr(null);
        Assertions.assertEquals(expected, actual.toString());
    }
}