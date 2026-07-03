package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
public abstract class ParseBase {

    private final Lexer lexer;

    public ParseBase(Lexer lexer) {
        this.lexer = lexer;
    }

    protected ParseException handleError(Token tok, List<TokTyp> typList) {
        lexer.scanToken();
        String msg = String.format("Parse Error: %s, tok: %s, pos: %s",
            "types expected " + typList,
            tok, tok.pos());
        log.error(msg);
        return new ParseException(msg);
    }

    protected Token expect(List<TokTyp> typList) {
        Token tok = nextToken();
        if (!typList.contains(tok.toktyp())) {
            throw handleError(tok, typList);
        }
        return tok;
    }

    protected Token nextToken() {
        return lexer.nextToken();
    }

    protected void eat() {
        lexer.scanToken();
    }

    protected boolean notInList(Token tok, List<TokTyp> typList) {
        for(TokTyp tokTyp : typList) {
            if(tok.toktyp().equals(tokTyp)) {
                return false;
            }
        }
        return true;
    }

}
