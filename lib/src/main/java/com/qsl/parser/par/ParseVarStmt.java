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
public class ParseVarStmt extends ParseBase {

    public ParseVarStmt(Lexer lexer) {
        super(lexer);
    }

    public TreeNode varStmt() {
        eat();
        Token identTok = expect(List.of(TokTyp.IDENT));
        eat();
        log.debug("varStmt: {}", identTok);
        expect(List.of(TokTyp.LBRACE));
        eat();
        List<TokTyp> clauseList = List.of(TokTyp.RBRACE,
            TokTyp.QT, TokTyp.ANS, TokTyp.COMP);
        List<TokTyp> terminateList = List.of(TokTyp.RBRACE);
        List<TreeNode> children = new ArrayList<>();
        Token tok = expect(clauseList);
        while (notInList(tok, terminateList)) {
            children.add(varClause());
            tok = expect(clauseList);
        }
        eat();
        return MultiNode.builder()
            .token(identTok)
            .children(children)
            .build();
    }

    private TreeNode varClause() {
        List<TokTyp> typList = List.of(
            TokTyp.QT, TokTyp.ANS, TokTyp.COMP);
        Token clauseTok = expect(typList);
        log.debug("varClause: {}", clauseTok);
        eat();
        return switch (clauseTok.toktyp()) {
            case QT -> {
                Token stringTok = expect(List.of(TokTyp.STRING));
                yield stringExpr(clauseTok, stringTok);
            }
            case ANS -> {
                Token charTok = expect(List.of(TokTyp.CHAR));
                yield charExpr(charTok);
            }
            case COMP -> compExpr(clauseTok);
            default -> throw new ParseException(
                "Unexpected token: " + clauseTok +
                    ", pos: " +  clauseTok.pos());
        };
    }

    private TreeNode compExpr(Token compTok) {
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

    private TreeNode stringExpr(Token clauseTok, Token stringTok) {
        eat();
        return MultiNode.builder()
            .token(clauseTok)
            .children(List.of(TerminalNode.builder()
                .token(stringTok).build()))
            .build();
    }

    private TreeNode charExpr(Token charTok) {
        log.debug("charExpr: {}", charTok);
        eat();

        List<TokTyp> typList = List.of(TokTyp.LPAREN,
            TokTyp.QT, TokTyp.ANS, TokTyp.RBRACE);
        Token tok = expect(typList);

        if (!TokTyp.LPAREN.equals(tok.toktyp())) {
            return TerminalNode.builder()
                .token(charTok).build();
        }

        eat();
        tok = expect(List.of(TokTyp.NUMBER,
            TokTyp.COMMA, TokTyp.RPAREN));
        List<TreeNode> numberList = new ArrayList<>();
        while (!TokTyp.RPAREN.equals(tok.toktyp())) {
            eat();
            if (TokTyp.NUMBER.equals(tok.toktyp())) {
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

}
