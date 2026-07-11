package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import lombok.Getter;

@Getter
public class ParseObjects {

    private final ParseStmts stmts;
    private final ParseVarStmt varExpr;
    private final ParseSumExpr sumExpr;
    private final ParseScriptStmt scriptStmt;
    private final ParseCommaExpr commaExpr;

    public ParseObjects(Lexer lexer) {
        stmts = new ParseStmts(lexer, this);
        sumExpr = new ParseSumExpr(lexer, this);
        varExpr = new ParseVarStmt(lexer, this);
        scriptStmt = new ParseScriptStmt(lexer, this);
        commaExpr = new ParseCommaExpr(lexer, this);
    }
}
