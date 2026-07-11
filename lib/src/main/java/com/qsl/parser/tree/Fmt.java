package com.qsl.parser.tree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Fmt {
    NL("\n"),
    LINE(" ");
    private final String sep;
}

