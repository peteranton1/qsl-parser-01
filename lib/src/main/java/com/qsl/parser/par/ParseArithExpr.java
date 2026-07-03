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

    private static final List<TokTyp> VALUE_EXPR = Arrays.asList(
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
        TreeNode left = parseProduct();
        Token tok = nextToken();
        if (SUM_OPS.contains(tok.toktyp())) {
            eat(); // consume + or -
            TreeNode right = parseSum();
            return InfixNode.builder()
                .token(tok)
                .left(left)
                .right(right)
                .build();
        }
        return left;
    }

    private TreeNode parseProduct() {
        TreeNode left = parseFactor();
        Token tok = nextToken();
        if (PROD_OPS.contains(tok.toktyp())) {
            eat(); // consume + or -
            TreeNode right = parseSum();
            return InfixNode.builder()
                .token(tok)
                .left(left)
                .right(right)
                .build();
        }

        return left;
    }

    private TreeNode parseFactor() {
        Token tok = expect(ARITH_TYPES);
        // consume PAREN
        if(TokTyp.LPAREN.equals(tok.toktyp())){
            eat(); // consume (
            TreeNode node = parseSum();
            expect(List.of(TokTyp.RPAREN));
            eat();
            return node;
        }
        // consume IDENT or NUMBER
        if(VALUE_EXPR.contains(tok.toktyp())){
            eat();
            return TerminalNode.builder()
                .token(tok)
                .build();
        }

        throw handleError(tok, VALUE_EXPR);
    }
}
