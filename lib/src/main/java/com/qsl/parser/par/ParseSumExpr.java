package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.IdentNode;
import com.qsl.parser.tree.InfixNode;
import com.qsl.parser.tree.TerminalNode;
import com.qsl.parser.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ParseSumExpr extends ParseBase {

    private static final List<TokTyp> SUM_TYPES = List.of(
        TokTyp.LPAREN,
        TokTyp.RPAREN,
        TokTyp.IDENT,
        TokTyp.NUMBER,
        TokTyp.STRING,
        TokTyp.COMMA,
        TokTyp.CHAR,
        TokTyp.QUANTITY,
        TokTyp.SUM_PLUS,
        TokTyp.SUM_MINUS,
        TokTyp.SUM_MULT,
        TokTyp.SUM_DIV
    );

    private static final List<TokTyp> VALUE_EXPR = List.of(
        TokTyp.NUMBER,
        TokTyp.STRING
    );

    private static final List<TokTyp> SUM_OPS = List.of(
        TokTyp.SUM_PLUS,
        TokTyp.SUM_MINUS,
        TokTyp.COMMA
    );

    private static final List<TokTyp> PROD_OPS = List.of(
        TokTyp.SUM_MULT,
        TokTyp.SUM_DIV
    );

    private final ParseObjects base;

    public ParseSumExpr(Lexer lexer, ParseObjects base) {
        super(lexer, base);
        this.base = base;
    }

    public TreeNode sumExpr() {
        // expect an arithmetic expression
        // until there are no more arithmetic tokens
        expect(SUM_TYPES);
        return parseSum();
    }

    private TreeNode parseSum() {
        TreeNode left = parseProduct();
        Token tok = nextToken();
        if(!SUM_OPS.contains(tok.toktyp())) {
            return left;
        }
        eat();
        TreeNode right = parseSum();
        return buildInfixExpr(tok, left, right);
    }

    private TreeNode parseProduct() {
        TreeNode left = parseFactor();
        Token tok = nextToken();
        if(!PROD_OPS.contains(tok.toktyp())) {
            return left;
        }
        eat();
        TreeNode right = parseProduct();
        return buildInfixExpr(tok, left, right);
    }

    private TreeNode buildInfixExpr(Token tok, TreeNode left, TreeNode right) {
        return InfixNode.builder()
            .token(tok)
            .left(left)
            .right(right)
            .build();
    }

    private TreeNode parseFactor() {
        Token tok = nextToken();
        if(TokTyp.LPAREN.equals(tok.toktyp())) {
            return parenExpr();
        }
        TreeNode expr = null;
        eat();
        // IDENT check for name(expr)
        List<TokTyp> identTypes = List.of(TokTyp.IDENT, TokTyp.CHAR, TokTyp.QUANTITY);
        if(identTypes.contains(tok.toktyp())) {
            Token subToken = nextToken();
            if(TokTyp.LPAREN.equals(subToken.toktyp())) {
                expr = parenExpr();
            }
            return buildIdentExpr(tok, expr);
        }
        // STRING or NUMBER
        if(VALUE_EXPR.contains(tok.toktyp())) {
            return buildNumberExpr(tok);
        }
        // temp
        throw parseException(tok);
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
