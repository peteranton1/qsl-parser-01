package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.AssignNode;
import com.qsl.parser.tree.ExecNode;
import com.qsl.parser.tree.ScriptNode;
import com.qsl.parser.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParseScriptStmt extends ParseBase {

    private static final List<TokTyp> SCRIPT_TYPES = List.of(
        TokTyp.SCRIPT
    );

    private static final List<TokTyp> EXEC_TYPES = List.of(
        TokTyp.EXEC
    );

    private final ParseObjects base;

    public ParseScriptStmt(Lexer lexer, ParseObjects base) {
        super(lexer, base);
        this.base = base;
    }

    public TreeNode scriptStmt() {
        // expect a block of script
        // until the whole script is processed.
        match(SCRIPT_TYPES);
        return script();
    }

    private TreeNode script() {

        Token identTok = match(List.of(TokTyp.IDENT));
        log.debug("script: {}", identTok);
        match(List.of(TokTyp.LBRACE));
        TreeNode node = scriptBraceBlock(identTok);
        match(List.of(TokTyp.RBRACE));
        return node;
    }

    private TreeNode scriptBraceBlock(Token identTok) {
        return ScriptNode.builder()
            .token(identTok)
            .children(List.of(execClause()))
            .build();
    }

    private TreeNode execClause() {
        expect(EXEC_TYPES);
        // Just support exec stmts in script blocks atm
        TreeNode node = execExpr();
        Token tok = nextToken();
        return node;
    }

    private TreeNode execExpr() {
        List<TokTyp> terminateList = List.of(
            TokTyp.RBRACE);
        List<TokTyp> typList = List.of(TokTyp.EXEC, TokTyp.RBRACE);
        List<TreeNode> children = new ArrayList<>();
        Token identTok = null;
        Token execTok = match(typList);
        while(notInList(execTok, terminateList)) {
            identTok = execTok;
            TreeNode node = base.getCommaExpr().commaExpr(identTok);
            children.add(node);
            execTok = expect(typList);
            if(notInList(execTok, terminateList)) {
                eat();
            }
        }
        return ExecNode.builder()
            .token(identTok)
            .children(children)
            .build();
    }

}
