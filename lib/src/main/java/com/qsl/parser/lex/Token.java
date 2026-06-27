package com.qsl.parser.lex;

public record Token(
    TokTyp toktyp,
    String literal,
    Pos pos
) {
}
