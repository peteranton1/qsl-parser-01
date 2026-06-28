package com.qsl.parser;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.par.Parser;
import com.qsl.parser.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParserRunner {

    public ParserRunner() {
    }

    public String runParser(String content) {
        // temporary
        // return callLexer(content);
        return callParser(content);
    }

    private String callParser(String content) {
        Lexer lexer = new Lexer(content);
        Parser parser = new Parser(lexer);
        TreeNode root = parser.prog();
        return toStringTreeNode(root, 1);
    }

    private String callLexer(String content) {
        Lexer lexer = new Lexer(content);
        List<Token> tokens = new ArrayList<>();
        Token token = null;
        do {
            token = lexer.scanToken();
            tokens.add(token);
        } while (token != null &&
            !TokTyp.EOF.equals(token.toktyp()));
        return toStringTokenList(tokens);
    }

    private String toStringTreeNode(TreeNode root, int indent) {
        StringBuilder sb = new StringBuilder();
        String indentStr = " ";
        if (indent > 1) {
            indentStr = " ".repeat(indent);
        }
        switch (root) {
            case InfixNode infix -> sb
                .append(indentStr)
                .append(infix.getToken())
                .append("\n")
                .append(toStringTreeNode(infix.getLeft(), indent + 1))
                .append(toStringTreeNode(infix.getRight(), indent + 1))
            ;
            case PrefixNode prefix -> sb
                .append(indentStr)
                .append(prefix.getToken())
                .append("\n")
                .append(toStringTreeNode(prefix.getArg(), indent + 1))
            ;
            case MultiNode multi -> {
                sb
                    .append(indentStr)
                    .append(multi.getToken())
                    .append("\n")
                ;
                for(TreeNode node: multi.getChildren()){
                    sb.append(toStringTreeNode(node, indent + 1));
                }
            }
            case TerminalNode terminal -> sb
                .append(indentStr)
                .append(terminal.getToken())
                .append("\n")
            ;
            default -> throw
                new IllegalStateException("Unexpected value: " + root);
        }
        return sb.toString();
    }

    private String toStringTokenList(List<Token> tokens) {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            if (!sb.isEmpty()) {
                sb.append(" ");
            }
            sb.append(token.toString());

        }
        return sb.toString();
    }
}
