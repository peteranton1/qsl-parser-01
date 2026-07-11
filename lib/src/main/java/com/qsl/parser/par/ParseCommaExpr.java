package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.print.ParseException;
import com.qsl.parser.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParseCommaExpr extends ParseBase {

    private static final List<TokTyp> TYP_LIST = List.of(TokTyp.EXEC);

    private static final List<TokTyp> EXPR_TOKENS = List.of(
        TokTyp.IDENT,
        TokTyp.NUMBER,
        TokTyp.STRING,
        TokTyp.COMMA
    );

    private final ParseObjects base;

    public ParseCommaExpr(Lexer lexer, ParseObjects base) {
        super(lexer, base);
        this.base = base;
    }

    public TreeNode commaExpr(Token execTok) {
        // expect a comma expression
        // until there are no more comma tokens
        if(!TYP_LIST.contains(execTok.toktyp())){
            throw parseException(execTok);
        }
        return parseCommaExpr(execTok);
    }

    private TreeNode parseCommaExpr(Token identTok) {
        List<TreeNode> children = new ArrayList<>();
        Token tok = nextToken();
        while (EXPR_TOKENS.contains(tok.toktyp())) {
            TreeNode factorNode = parseFactor(identTok);
            children.add(factorNode);
            tok = nextToken();
        }
        match(List.of(TokTyp.SEMICOLON));
        return ExecNode.builder()
            .token(identTok)
            .children(children)
            .build();
    }

    // example EXEC q1, q2(1), q3("2") ;
    private TreeNode parseFactor(Token identTok) {
        Token tok = nextToken();
        if (TokTyp.LPAREN.equals(tok.toktyp())) {
            return parenExpr(identTok);
        }
        switch (tok.toktyp()) {
            case COMMA -> {
                eat();
                return parseFactor(identTok);
            }
            case IDENT -> {
                TreeNode expr = null;
                eat();
                Token subToken = nextToken();
                if (TokTyp.LPAREN.equals(subToken.toktyp())) {
                    expr = parenExpr(identTok);
                }
                return buildIdentExpr(tok, expr);
            }
            case NUMBER, STRING -> {
                eat();
                return buildNumberExpr(tok);
            }
            default -> throw new ParseException("Unexpected token " + tok.toktyp());
        }
    }

    private TreeNode parenExpr(Token identTok) {
        eat();
        TreeNode expr = parseFactor(identTok);
        match(List.of(TokTyp.RPAREN));
        return expr;
    }

    private TreeNode buildIdentExpr(Token tok, TreeNode expr) {
        return IdentNode.builder()
            .token(tok)
            .expr(expr)
            .build();
    }

    private TreeNode buildNumberExpr(Token tok) {
        return TerminalNode.builder()
            .token(tok)
            .build();
    }
}
