package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import lombok.Getter;

@Getter
public class ParseObjects {

    private final ParseStmts stmts;
    private final ParseVarStmt varExpr;
    private final ParseSumExpr arithExpr;

    public ParseObjects(Lexer lexer) {
        stmts = new ParseStmts(lexer, this);
        arithExpr = new ParseSumExpr(lexer, this);
        varExpr = new ParseVarStmt(lexer, this);
    }
}
