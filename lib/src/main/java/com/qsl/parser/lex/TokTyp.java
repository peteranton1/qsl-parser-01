package com.qsl.parser.lex;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum TokTyp {
    // keywords first
    ANS("ans"),
    CHAR("char"),
    COMP("comp"),
    EXEC("exec"),
    QT("qt"),
    VAR("var"),
    // identifiers and rest of tokens
    IDENT("\\b[a-zA-Z_$][a-zA-Z0-9_$]*\\b"),
    NUMBER("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"),
    STRING("\"([^\\\\\\\"]|\\\\.)*\""),
    LBRACE("\\{"),
    RBRACE("}"),
    LBRACKET("\\["),
    RBRACKET("]"),
    LPAREN("\\("),
    RPAREN("\\)"),
    COMMA(","),
    DOT("\\."),
    MULT("\\*"),
    DIV("/"),
    PLUS("\\+"),
    MINUS("-"),
    SEMICOLON(";"),
    WHITESPACE("^[ \t\r\n]+"),
    UNKNOWN("UNKNOWN"),
    EOF("\\[EOF\\]");

    private final Pattern pattern;

    TokTyp(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

}
