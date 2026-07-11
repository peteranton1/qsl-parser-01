package com.qsl.parser.print;

import com.qsl.parser.lex.Token;

import java.util.List;

public class TokenPrinter {

    public String printTokens(List<Token> tokens, int width) {
        StringBuilder sb = new StringBuilder();
        int col = 0;
        for (Token token : tokens) {
            if (col == width) {
                sb.append("\n");
                col = 0;
            }
            sb.append(" ");
            sb.append(token.toString());
            col++;
        }
        return sb.toString();
    }
}
