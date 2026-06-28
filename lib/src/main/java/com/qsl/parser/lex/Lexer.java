package com.qsl.parser.lex;

public class Lexer {
    private final StringReader reader;

    private Token currentToken;
    private Token nextToken;

    public Lexer(String input) {
        this.reader = new StringReader(input);
        this.currentToken = null;
        this.nextToken = nonWhitespaceToken();
    }

    public Token scanToken() {
        if (nextToken == null) {
            nextToken = reader.getEofToken();
        }
        currentToken = nextToken;
        nextToken = nonWhitespaceToken();
        return currentToken;
    }

    public Token nextToken() {
        return nextToken;
    }

    private Token nonWhitespaceToken() {
        Token token = reader.nextToken();
        while (token != null &&
            TokTyp.WHITESPACE.equals(token.toktyp())) {
            token = reader.nextToken();
        }
        return token;
    }

}
