package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ParseSumExpr extends ParseBase {

    private static final List<TokTyp> SUM_TYPES = Arrays.asList(
        TokTyp.LPAREN,
        TokTyp.RPAREN,
        TokTyp.IDENT,
        TokTyp.NUMBER,
        TokTyp.STRING,
        TokTyp.SUM_PLUS,
        TokTyp.SUM_MINUS,
        TokTyp.SUM_MULT,
        TokTyp.SUM_DIV
    );

    private static final List<TokTyp> VALUE_EXPR = Arrays.asList(
        TokTyp.NUMBER,
        TokTyp.STRING
    );

    private static final List<TokTyp> SUM_OPS = Arrays.asList(
        TokTyp.SUM_PLUS,
        TokTyp.SUM_MINUS
    );

    private static final List<TokTyp> PROD_OPS = Arrays.asList(
        TokTyp.SUM_MULT,
        TokTyp.SUM_DIV
    );

    private final ParseObjects base;

    public ParseSumExpr(Lexer lexer, ParseObjects base) {
        super(lexer, base);
        this.base = base;
    }

    public TreeNode sumExpr(Token parentTok) {
        // expect an arithmetic expression
        // until there are no more arithmetic tokens
        Token tok = expect(SUM_TYPES);

        TreeNode expr = parseSum();

        return expr;
    }

    private TreeNode parseSum() {
        Token tok = nextToken();
        TreeNode left = parseProduct();
        // temp
        return left;
    }

    private TreeNode parseProduct() {
        Token tok = nextToken();
        TreeNode left = parseFactor();
        // temp
        return left;
    }

    private TreeNode parseFactor() {
        Token tok = nextToken();
        if(TokTyp.LPAREN.equals(tok.toktyp())) {
            return parenExpr();
        }
        TreeNode expr = null;
        eat();
        // IDENT check for name(expr)
        if(TokTyp.IDENT.equals(tok.toktyp())) {
            Token subToken = nextToken();
            if(TokTyp.LPAREN.equals(subToken.toktyp())) {
                expr = parenExpr();
            }
            return buildSumExpr(tok, expr);
        }
        // STRING or NUMBER
        if(VALUE_EXPR.contains(tok.toktyp())) {
            return buildNumberExpr(tok);
        }
        // temp
        throw parseException(tok);
    }

    private TreeNode buildSumExpr(Token tok, TreeNode expr) {
        return SumNode.builder()
            .token(tok)
            .args(expr)
            .build();
    }

    private TreeNode buildNumberExpr(Token tok) {
        return TerminalNode.builder()
            .token(tok)
            .build();
    }

    private TreeNode parenExpr() {
        eat();
        TreeNode expr = parseSum();
        Token tok = nextToken();
        if(TokTyp.RPAREN.equals(tok.toktyp())) {
            eat();
        }
        return expr;
    }
}
