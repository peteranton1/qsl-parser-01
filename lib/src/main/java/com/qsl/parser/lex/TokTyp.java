package com.qsl.parser.lex;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum TokTyp {
    // keywords first
    ANS("ans"),
    CHAR("char"),
    COMP("comp"),
    ELSE("else"),
    EXEC("exec"),
    FN("fn"),
    IF("if"),
    IMPORT("import"),
    PACKAGE("package"),
    QT("qt"),
    QUANTITY("quant"),
    RETURN("return"),
    SCRIPT("script"),
    VAR("var"),
    WHILE("while"),
    // identifiers and rest of tokens,
    IDENT("\\b[a-zA-Z_$][a-zA-Z0-9_$]*\\b"),
    NUMBER("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"),
    STRING("\"([^\\\\\\\"]|\\\\.)*\""),
    COMMENTS("(//.*?$)|(/\\*.*?\\*/)"),
    LBRACE("\\{"),
    RBRACE("}"),
    LBRACKET("\\["),
    RBRACKET("]"),
    LPAREN("\\("),
    RPAREN("\\)"),
    COMMA(","),
    DOT("\\."),
    LOG_EQ("=="),
    ASSIGN("="),
    LOG_NEQ("!="),
    LOG_GE(">="),
    LOG_LE("<="),
    LOG_AND_AND("&&"),
    LOG_OR_OR("\\|\\|"),
    SUM_MULT("\\*"),
    SUM_DIV("/"),
    SUM_PLUS("\\+"),
    SUM_MINUS("-"),
    SEMICOLON(";"),
    WHITESPACE("^[ \t\r\n]+"),
    UNKNOWN("UNKNOWN"),
    EOF("\\[EOF\\]");

    private final Pattern pattern;

    TokTyp(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

}
