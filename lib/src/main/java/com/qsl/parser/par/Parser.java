package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
@Slf4j
public class Parser {

    private final Lexer lexer;

    private Token peek() {
        return lexer.nextToken();
    }

    private Token expect(List<TokTyp> typList) {
        Token tok = peek();
        if (!typList.contains(tok.toktyp())) {
            throw handleError(tok);
        }
        return tok;
    }

    private void eat() {
        lexer.scanToken();
    }

    private boolean notInList(Token tok, List<TokTyp> typList) {
        for(TokTyp tokTyp : typList) {
            if(tok.toktyp().equals(tokTyp)) {
                return false;
            }
        }
        return true;
    }

    public TreeNode prog() {
        return stmts();
    }

    public TreeNode stmts() {
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

    public TreeNode stmt() {
        log.debug("stmt: ");
        List<TokTyp> stmtTypes = List.of(TokTyp.EXEC, TokTyp.VAR);
        Token tok = expect(stmtTypes);
        return switch (tok.toktyp()) {
            case VAR -> varStmt();
            case EXEC -> execStmt(tok);
            default -> throw handleError(tok);
        };
    }

    public TreeNode varStmt() {
        eat();
        Token identTok = expect(List.of(TokTyp.IDENT));
        eat();
        log.debug("varStmt: {}", identTok);
        List<TreeNode> list = new ArrayList<>();
        expect(List.of(TokTyp.LBRACE));
        eat();
        List<TokTyp> clauseList = List.of(
            TokTyp.QT, TokTyp.ANS, TokTyp.RPAREN, TokTyp.RBRACE);
        List<TokTyp> terminateList = List.of(TokTyp.RPAREN, TokTyp.RBRACE);
        Token tok = expect(clauseList);
        while (notInList(tok, terminateList)) {
            list.add(varClause());
            tok = expect(clauseList);
        }
        eat();
        return MultiNode.builder()
            .token(identTok)
            .children(list)
            .build();
    }

    public TreeNode varClause() {
        List<TokTyp> typList = List.of(TokTyp.QT, TokTyp.ANS);
        Token clauseTok = expect(typList);
        log.debug("varClause: {}", clauseTok);
        eat();
        if(TokTyp.QT.equals(clauseTok.toktyp())) {
            Token stringTok = expect(List.of(TokTyp.STRING));
            eat();
            return PrefixNode.builder()
                .token(clauseTok)
                .arg(TerminalNode.builder()
                    .token(stringTok).build())
                .build();
        } else if(TokTyp.ANS.equals(clauseTok.toktyp())) {
            Token charTok = expect(List.of(TokTyp.CHAR));
            return charExpr(charTok);
        }
        throw handleError(clauseTok);
    }

    private TreeNode charExpr(Token charTok) {
        log.debug("charExpr: {}", charTok);
        eat();

        List<TokTyp> typList = List.of(TokTyp.LPAREN,
            TokTyp.QT, TokTyp.ANS, TokTyp.RBRACE);
        Token tok = expect(typList);

        if(!TokTyp.LPAREN.equals(tok.toktyp())) {
            return TerminalNode.builder()
                .token(charTok).build();
        }

        eat();
        tok = expect(List.of(TokTyp.NUMBER,
            TokTyp.COMMA, TokTyp.RPAREN));
        List<TreeNode> numberList = new ArrayList<>();
        while(!TokTyp.RPAREN.equals(tok.toktyp())) {
            eat();
            if(TokTyp.NUMBER.equals(tok.toktyp())) {
                numberList.add(
                    TerminalNode
                        .builder()
                        .token(tok)
                        .build());
            }
            tok = expect(List.of(TokTyp.NUMBER,
                TokTyp.COMMA, TokTyp.RPAREN));
        }
        eat();
        return MultiNode.builder()
            .token(charTok)
            .children(numberList)
            .build();
    }

    public TreeNode execStmt(Token stmtTok) {
        log.debug("execStmt: {}", stmtTok);
        expect(List.of(TokTyp.EXEC));
        // Temporary
        throw handleError(stmtTok);
    }

    private ParseException handleError(Token tok) {
        lexer.scanToken();
        String msg = String.format("Parse Error: %s, pos: %s",
            tok, tok.pos());
        log.error(msg);
        return new ParseException(msg);
    }
}
