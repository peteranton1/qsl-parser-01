package com.qsl.parser;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.par.Parser;
import com.qsl.parser.print.Fmt;
import com.qsl.parser.print.TokenPrinter;
import com.qsl.parser.print.TreePrinter;
import com.qsl.parser.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParserRunner {

    public ParserRunner() {
    }

    public String runParser(String content) {
        // return callLexer(content);
        return callParser(content);
    }

    public String runLexer(String content) {
        return callLexer(content);
    }

    private String callParser(String content) {
        Lexer lexer = new Lexer(content);
        Parser parser = new Parser(lexer);
        TreePrinter treePrinter = new TreePrinter();
        TreeNode root = parser.prog();
        return treePrinter.printTree(root, Fmt.NL);
    }

    private String callLexer(String content) {
        Lexer lexer = new Lexer(content);
        List<Token> tokens = new ArrayList<>();
        TokenPrinter tokenPrinter = new TokenPrinter();
        Token token ;
        do {
            token = lexer.scanToken();
            tokens.add(token);
        } while (token != null &&
            !TokTyp.EOF.equals(token.toktyp()));
        final int width = 3;
        return tokenPrinter.printTokens(tokens, width);
    }

}
