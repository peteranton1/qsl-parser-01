package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.InfixNode;
import com.qsl.parser.tree.MultiNode;
import com.qsl.parser.tree.TerminalNode;
import com.qsl.parser.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ParseArithExpr extends ParseBase {

    private static final List<TokTyp> ARITH_TYPES = Arrays.asList(
        TokTyp.LPAREN,
        TokTyp.RPAREN,
        TokTyp.IDENT,
        TokTyp.NUMBER,
        TokTyp.PLUS,
        TokTyp.MINUS,
        TokTyp.MULT,
        TokTyp.DIV
    );

    private static final List<TokTyp> EXPR_START = Arrays.asList(
        TokTyp.IDENT,
        TokTyp.NUMBER
    );

    private static final List<TokTyp> SUM_OPS = Arrays.asList(
        TokTyp.PLUS,
        TokTyp.MINUS
    );

    private static final List<TokTyp> PROD_OPS = Arrays.asList(
        TokTyp.MULT,
        TokTyp.DIV
    );

    public ParseArithExpr(Lexer lexer) {
        super(lexer);
    }

    public TreeNode arithExpr(Token compTok) {
        return MultiNode.builder()
            .token(compTok)
            .children(List.of(parseSum()))
            .build();
    }

    private TreeNode parseSum() {
        Token tok = expect(ARITH_TYPES);
        if (tok.toktyp() == TokTyp.LPAREN) {
            eat();
            TreeNode resultNode = parseSum();
            expect(List.of(TokTyp.RPAREN));
            eat();
            return resultNode;
        } else if (EXPR_START.contains(tok.toktyp())) {
            TreeNode prod1 = parseProduct();
            tok = nextToken();
            if (SUM_OPS.contains(tok.toktyp())) {
                eat();
                return InfixNode.builder()
                    .left(prod1)
                    .op(tok)
                    .right(parseProduct())
                    .build();
            } else {
                return prod1;
            }
        } else {
            throw handleError(tok, EXPR_START);
        }
    }

    private TreeNode parseProduct() {
        Token tok = expect(ARITH_TYPES);
        if (tok.toktyp() == TokTyp.LPAREN) {
            eat();
            TreeNode resultNode = parseProduct();
            expect(List.of(TokTyp.RPAREN));
            eat();
            return resultNode;
        } else if (EXPR_START.contains(tok.toktyp())) {
            TreeNode fac1 = parseFactor();
            tok = nextToken();
            if (PROD_OPS.contains(tok.toktyp())) {
                eat();
                return InfixNode.builder()
                    .left(fac1)
                    .op(tok)
                    .right(parseFactor())
                    .build();
            } else {
                return fac1;
            }
        } else {
            throw handleError(tok, EXPR_START);
        }
    }

    private TreeNode parseFactor() {
        Token tok = expect(ARITH_TYPES);
        if (tok.toktyp() == TokTyp.LPAREN) {
            eat();
            TreeNode resultNode = parseFactor();
            expect(List.of(TokTyp.RPAREN));
            eat();
            return resultNode;
        } else if (EXPR_START.contains(tok.toktyp())) {
            eat();
            return TerminalNode.builder()
                .token(tok)
                .build();
        } else {
            return parseSum();
        }
    }
}
