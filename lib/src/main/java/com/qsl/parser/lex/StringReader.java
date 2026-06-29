package com.qsl.parser.lex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReader {

    private final String input;
    private final StringBuilder buffer;
    private int pos;
    private int line;
    private int col;

    public StringReader(String input) {
        this.input = input;
        this.buffer = new StringBuilder(input);
        this.pos = 0;
        this.line = 1;
        this.col = 0;
    }

    private void skip(int step) {
        for (int i = 0; i < step; i++) {
            if (pos >= input.length()) {
                return;
            }
            buffer.deleteCharAt(0);
            char c = input.charAt(pos);
            pos++;
            if (c == '\n') {
                line++;
                col = 0;
            } else {
                col++;
            }
        }
    }

    public Token nextToken() {
        if (buffer.isEmpty()) {
            return getEofToken();
        }
        for (TokTyp tokTyp : TokTyp.values()) {
            if (TokTyp.EOF == tokTyp ||
                TokTyp.UNKNOWN == tokTyp) {
                break;
            }
            Pattern pattern = tokTyp.getPattern();
            Matcher matcher = pattern.matcher(buffer);
            if (matcher.find() && matcher.start() == 0) {
                String literal = matcher.group();
                Token token = new Token(tokTyp,
                    literal,
                    getPos());
                skip(literal.length());
                return token;
            }
        }
        if (pos < input.length()) {
            Token unknownToken = getUnknownToken();
            skip(1);
            return unknownToken;
        }

        return getEofToken();
    }

    public Token getUnknownToken() {
        int posEnd = 20;
        if (buffer.length() < posEnd) {
            posEnd = buffer.length();
        }
        return new Token(TokTyp.UNKNOWN,
            buffer.substring(0, posEnd),
            getPos());
    }

    public Token getEofToken() {
        return Token.builder()
            .toktyp(TokTyp.EOF)
            .literal(TokTyp.EOF.getPattern().pattern())
            .pos(getPos())
            .build();
    }

    public Pos getPos() {
        return Pos.builder()
            .pos(pos)
            .line(line)
            .col(col)
            .build();
    }
}
