package com.qsl.parser.lex;

import lombok.Builder;

@Builder
public record Pos(int pos, int line, int col) {
    @Override
    public String toString() {
        return "[pos:" + pos + ",line:" + line + ",col:" + col + "]";
    }
}
