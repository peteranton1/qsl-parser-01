package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.Pos;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.MultiNode;
import com.qsl.parser.tree.TerminalNode;
import com.qsl.parser.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParseExecStmt extends ParseBase {

    private final IdentGenerator identGenerator;

    public ParseExecStmt(Lexer lexer) {
        super(lexer);
        identGenerator = new IdentGenerator("exec", 4);
    }

    public TreeNode execStmt() {
        Token execTok = expect(List.of(TokTyp.EXEC));
        eat();

        Token tok = generateIdentTok(execTok.pos());
        log.debug("execStmt: {}", tok);

        List<TreeNode> children = new ArrayList<>();
        children.add(TerminalNode.builder()
            .token(tok)
            .build());

        expect(List.of(TokTyp.LPAREN));
        eat();

        List<TokTyp> allowed = List.of(TokTyp.RPAREN,
            TokTyp.COMMA, TokTyp.IDENT);
        tok = expect(allowed);
        while (!TokTyp.RPAREN.equals(tok.toktyp())) {
            eat();
            if (TokTyp.IDENT.equals(tok.toktyp())) {
                children.add(TerminalNode.builder()
                    .token(tok)
                    .build());
            }
            tok = expect(allowed);
        }
        eat();
        return MultiNode.builder()
            .token(execTok)
            .children(children)
            .build();
    }

    private Token generateIdentTok(Pos pos) {
        String identStr = identGenerator.generateIdent();
        return Token.builder()
            .toktyp(TokTyp.IDENT)
            .literal(identStr)
            .pos(pos)
            .build();
    }
}
