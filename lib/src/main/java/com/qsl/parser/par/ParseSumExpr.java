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
        TokTyp.SEMICOLON,
        TokTyp.STRING,
        TokTyp.SUM_PLUS,
        TokTyp.SUM_MINUS,
        TokTyp.SUM_MULT,
        TokTyp.SUM_DIV
    );

    private static final List<TokTyp> VALUE_EXPR = Arrays.asList(
        TokTyp.IDENT,
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

    public TreeNode sumExpr(Token compTok) {
        return SumNode.builder()
            .token(compTok)
            .args(parseSum())
            .build();
    }

    private TreeNode parenSumExpr() {
        eat(); // consume (
        TreeNode node = parseSum();
        expect(List.of(TokTyp.RPAREN));
        eat();
        return node;
    }

    private TreeNode parseSum() {
        Token tok = nextToken();
        TreeNode left = parseProduct();
        if (!SUM_OPS.contains(tok.toktyp())) {
            return left;
        }
        eat(); // consume + or -
        TreeNode right = parseSum();
        return InfixNode.builder()
            .token(tok)
            .left(left)
            .right(right)
            .build();
    }

    private TreeNode parseProduct() {
        TreeNode left = parseFactor();
        Token tok = nextToken();

        if (!PROD_OPS.contains(tok.toktyp())) {
            return left;
        }
        eat(); // consume + or -
        TreeNode right = parseSum();
        return InfixNode.builder()
            .token(tok)
            .left(left)
            .right(right)
            .build();
    }

    private TreeNode parseFactor() {
        Token tok = expect(SUM_TYPES);
        // consume PAREN
        if(TokTyp.LPAREN.equals(tok.toktyp())){
            return parenSumExpr();
        }
        // consume IDENT, NUMBER, STRING
        if(VALUE_EXPR.contains(tok.toktyp())){
            eat();
            Token next = nextToken();
            if(TokTyp.IDENT.equals(tok.toktyp()) &&
                TokTyp.LPAREN.equals(next.toktyp())){
                return SumNode.builder()
                    .token(tok)
                    .args(parenSumExpr())
                    .build();
            }
            return TerminalNode.builder()
                .token(tok)
                .build();
        }

        throw handleError(tok, VALUE_EXPR);
    }
}
