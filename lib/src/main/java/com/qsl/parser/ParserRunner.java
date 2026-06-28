package com.qsl.parser;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;

import java.util.ArrayList;
import java.util.List;

public class ParserRunner {

    public ParserRunner() {
    }

    public String runParser(String content) {
        // temporary
        return callLexer(content);
    }

    private String callLexer(String content) {
        Lexer lexer = new Lexer(content);
        List<Token> tokens = new ArrayList<>();
        Token token = null;
        do {
            token = lexer.nextToken();
            tokens.add(token);
        } while (token != null &&
            !TokTyp.EOF.equals(token.toktyp()));
        return toStringTokenList(tokens);
    }

    private String toStringTokenList(List<Token> tokens) {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            if(!sb.isEmpty()){
                sb.append(" ");
            }
            sb.append(token.toString());

        }
        return sb.toString();
    }
}
