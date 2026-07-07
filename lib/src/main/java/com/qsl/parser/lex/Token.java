package com.qsl.parser.lex;

import lombok.Builder;

import java.util.Objects;

@Builder
public record Token(
    TokTyp toktyp,
    String literal,
    Pos pos
) {

    @Override
    public String toString() {
        return "[" + toktyp.toString() + ":" + literal + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return toktyp == token.toktyp && Objects.equals(literal, token.literal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toktyp, literal);
    }
}
