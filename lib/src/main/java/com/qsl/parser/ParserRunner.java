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
        // return callLexer(content);
        return callParser(content);
    }

    public String runLexer(String content) {
        return callLexer(content);
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
        Token token ;
        do {
            token = lexer.scanToken();
            tokens.add(token);
        } while (token != null &&
            !TokTyp.EOF.equals(token.toktyp()));
        return toStringTokenList(tokens);
    }

    private String toStringToken(Token token, int indent) {
        StringBuilder sb = new StringBuilder();
        String indentStr = " ";
        if (indent > 1) {
            indentStr = " ".repeat(indent);
        }
        sb.append(indentStr)
            .append(token)
            .append("\n");
        return sb.toString();
    }

    private int infixCalc(TreeNode node, int indent) {
        int indentNext = indent;
        if(node instanceof InfixNode){
            indentNext++;
        }
        return indentNext;
    }

    private String toStringInfixNode(InfixNode infix, int indent) {
        StringBuilder sb = new StringBuilder();
        String indentStr = " ";
        if (indent > 1) {
            indentStr = " ".repeat(indent);
        }
        int indentLeft = infixCalc(infix.getLeft(), indent);
        int indentRight = infixCalc(infix.getRight(), indent);

        sb.append(toStringTreeNode(infix.getLeft(), indentLeft));
        sb.append(toStringToken(infix.getToken(), indent));
        sb.append(toStringTreeNode(infix.getRight(), indentRight));
        return sb.toString();
    }

    private String toStringTreeNode(TreeNode root, int indent) {
        StringBuilder sb = new StringBuilder();
        String indentStr = " ";
        if (indent > 1) {
            indentStr = " ".repeat(indent);
        }
        switch (root) {
            case MultiNode multi -> {
                sb
                    .append(indentStr)
                    .append(multi.getToken())
                    .append("\n")
                ;
                for (TreeNode node : multi.getChildren()) {
                    sb.append(toStringTreeNode(node, indent + 1));
                }
            }
            case InfixNode infix ->
                sb.append(toStringInfixNode(infix, indent));

            case IdentNode ident -> sb
                .append(indentStr)
                .append(ident.getToken())
                .append("\n");

            case AssignNode ident -> sb
                .append(indentStr)
                .append("VAR ")
                .append(ident.getToken())
                .append("\n")
                .append(indentStr)
                .append(" ")
                .append(toStringTreeNode(ident.getArgs(), indent+ 1))
                .append("\n");

            case ComputeNode ident -> sb
                .append(indentStr)
                .append("COMP ")
                .append(ident.getToken())
                .append("\n")
                .append(indentStr)
                .append(" ")
                .append(toStringTreeNode(ident.getArgs(), indent+ 1))
                .append("\n");

            case TerminalNode terminal -> sb
                .append(indentStr)
                .append(terminal.getToken())
                .append("\n");

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
