package com.qsl.parser.lex;

public class Lexer {
    private final StringReader reader;

    public Lexer(String input) {
        this.reader = new StringReader(input);
    }

    public Token readToken() {
        // placeholder
        return new Token(TokTyp.EOF, "EOF", reader.getPos());
    }
}
