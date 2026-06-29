package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.MultiNode;
import com.qsl.parser.tree.TerminalNode;
import com.qsl.parser.tree.TreeNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class Parser extends ParseBase {

    private final ParseVarStmt parseVarStmt;
    private final ParseExecStmt parseExecStmt;

    public Parser(Lexer lexer) {
        super(lexer);
        parseVarStmt = new ParseVarStmt(getLexer());
        parseExecStmt = new ParseExecStmt(getLexer());
    }

    public TreeNode prog() {
        return stmts();
    }

    private TreeNode stmts() {
        log.debug("stmts: ");
        List<TokTyp> stmtTypes = List.of(
            TokTyp.EXEC, TokTyp.VAR,
            TokTyp.EOF, TokTyp.UNKNOWN);
        Token tok = expect(stmtTypes);
        List<TreeNode> list = new ArrayList<>();
        List<TokTyp> typList = List.of(TokTyp.EOF, TokTyp.UNKNOWN);
        while (notInList(tok, typList)) {
            list.add(stmt());
            tok = expect(stmtTypes);
        }
        log.debug("stmts: {}", list.size());
        return new MultiNode(tok, list);
    }

    private TreeNode stmt() {
        log.debug("stmt: ");
        List<TokTyp> stmtTypes = List.of(TokTyp.EXEC, TokTyp.VAR);
        Token tok = expect(stmtTypes);
        return switch (tok.toktyp()) {
            case VAR -> varStmt();
            case EXEC -> execStmt();
            default -> throw handleError(tok, stmtTypes);
        };
    }

    public TreeNode varStmt() {
        return parseVarStmt.varStmt();
    }

    public TreeNode execStmt() {
        return parseExecStmt.execStmt();
    }

}
