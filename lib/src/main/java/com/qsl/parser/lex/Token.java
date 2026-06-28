package com.qsl.parser.lex;

import org.jspecify.annotations.NonNull;

public record Token(
    TokTyp toktyp,
    String literal,
    Pos pos
) {

    @Override
    @NonNull
    public String toString() {
        return "[" + toktyp.toString() + ":" + literal + "]";
    }
}
