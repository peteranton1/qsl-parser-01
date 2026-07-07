package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.MultiNode;
import com.qsl.parser.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParseStmts extends ParseBase {
    public ParseStmts(Lexer lexer, ParseObjects base) {
        super(lexer, base);
    }

    public TreeNode stmts() {
        log.debug("stmts: ");
        List<TokTyp> stmtTypes = List.of(
            TokTyp.VAR,
            TokTyp.FN,
            TokTyp.SCRIPT,
            TokTyp.IF,
            TokTyp.WHILE,
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
        List<TokTyp> stmtTypes = List.of(
            TokTyp.VAR,
            TokTyp.FN,
            TokTyp.SCRIPT,
            TokTyp.IF,
            TokTyp.WHILE);
        Token tok = expect(stmtTypes);
        return switch (tok.toktyp()) {
            case VAR -> varStmt();
            case FN -> fnStmt();
            case SCRIPT -> scriptStmt();
            case IF -> ifStmt();
            case WHILE -> whileStmt();
            default -> throw handleError(tok, stmtTypes);
        };
    }

    public TreeNode varStmt() {
        return null;
    }

    public TreeNode fnStmt() {
        return null;
    }

    public TreeNode scriptStmt() {
        return null;
    }

    public TreeNode ifStmt() {
        return null;
    }

    public TreeNode whileStmt() {
        return null;
    }
}
