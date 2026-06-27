package com.qsl.parser.lex;

public class StringReader {

    private final String input;
    private int pos;
    private int line;
    private int col;

    public StringReader(String input) {
        this.input = input;
        this.pos = 0;
        this.line = 1;
        this.col = 0;
    }

    public char nextChar() {
        if (pos >= input.length()) {
            return '\0';
        }
        char c = input.charAt(pos);
        pos++;
        if (c == '\n') {
            line++;
            col = 0;
        } else {
            col++;
        }
        return c;
    }

    public char peekChar() {
        if (pos >= input.length()-1) {
            return '\0';
        }
        return input.charAt(pos + 1);
    }

    public Pos getPos() {
        return new Pos(pos, line, col);
    }
}
