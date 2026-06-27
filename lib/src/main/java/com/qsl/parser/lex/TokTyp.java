package com.qsl.parser.lex;

public enum TokTyp {
    IDENT("^[a-zA-Z_$][\\w$]*$"),
    NUMBER("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"),
    STRING("\"([^\\\\\\\"]|\\\\.)*\""),
    LBRACE("\\{"),
    RBRACE("}"),
    LBRACKET("\\["),
    RBRACKET("]"),
    LPAREN("\\("),
    RPAREN("\\)"),
    COMMA(","),
    DOT("."),
    SEMICOLON(";"),
    VAR("var"),
    EXEC("exec"),
    EOF("EOF");

    private final String regex;

    TokTyp(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
