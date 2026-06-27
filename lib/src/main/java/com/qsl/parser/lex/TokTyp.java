package com.qsl.parser.lex;

import java.util.regex.Pattern;

public enum TokTyp {
    IDENT("\\b[a-zA-Z_$][a-zA-Z0-9_$]*\\b"),
    EXEC("^exec"),
    VAR("^var"),
    NUMBER("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"),
    STRING("^\"([^\\\\\\\"]|\\\\.)*\""),
    LBRACE("^\\{"),
    RBRACE("^}"),
    LBRACKET("^\\["),
    RBRACKET("^]"),
    LPAREN("^\\("),
    RPAREN("^\\)"),
    COMMA("^,"),
    DOT("^."),
    SEMICOLON("^;"),
    WHITESPACE("^[ \t\r\n]+"),
    EOF("^\\|EOF\\|");

    private final Pattern pattern;

    TokTyp(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
