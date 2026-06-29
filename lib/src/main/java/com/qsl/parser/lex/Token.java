package com.qsl.parser.lex;

import lombok.Builder;

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
}
