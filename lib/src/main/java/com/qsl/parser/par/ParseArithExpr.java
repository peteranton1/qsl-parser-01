package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.MultiNode;
import com.qsl.parser.tree.TerminalNode;
import com.qsl.parser.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParseArithExpr extends ParseBase {

    public ParseArithExpr(Lexer lexer) {
        super(lexer);
    }

    public TreeNode arithExpr(Token compTok) {
        // temporary implementation
        expect(List.of(TokTyp.LPAREN));
        eat();
        List<TokTyp> typList = List.of(TokTyp.RPAREN,
            TokTyp.IDENT, TokTyp.NUMBER,
            TokTyp.MULT, TokTyp.DIV,
            TokTyp.PLUS, TokTyp.MINUS);
        List<TreeNode> children = new ArrayList<>();
        Token tok = expect(typList);
        while (notInList(tok, List.of(TokTyp.RPAREN))) {
            eat();
            children.add(TerminalNode.builder()
                .token(tok)
                .build());
            tok = expect(typList);
        }
        eat();
        return MultiNode.builder()
            .token(compTok)
            .children(children)
            .build();
    }

}
