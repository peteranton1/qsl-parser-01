package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ParseVarStmt extends ParseBase {

    private static final List<TokTyp> VAR_TYPES = Arrays.asList(
        TokTyp.LBRACE,
        TokTyp.ASSIGN
    );

    public ParseVarStmt(Lexer lexer, ParseObjects base) {
        super(lexer, base);
    }

    public TreeNode varStmt() {
        eat();
        Token identTok = expect(List.of(TokTyp.IDENT));
        eat();
        log.debug("varStmt: {}", identTok);
        Token tok = expect(VAR_TYPES);
        TreeNode node = null;
        if(TokTyp.LBRACE.equals(tok.toktyp())) {
            eat();
            node = varBraceBlock(identTok);
            if(TokTyp.RBRACE.equals(nextToken().toktyp())) {
                eat();
            }
        } else if (TokTyp.ASSIGN.equals(tok.toktyp())) {
            node = varAssignStmt(identTok);
        }
        return AssignNode.builder()
            .token(identTok)
            .args(node)
            .build();
    }

    private TreeNode varAssignStmt(Token identTok) {
        eat();
        TreeNode expr = base.getSumExpr().sumExpr(identTok);
        expect(List.of(TokTyp.SEMICOLON));
        eat();
        return expr;
    }

    private TreeNode varBraceBlock(Token identTok) {
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
        Token tok = expect(typList);
        log.debug("varClause: {}", tok);
        eat();
        return switch (tok.toktyp()) {
            case QT -> {
                Token stringTok = expect(List.of(TokTyp.STRING));
                yield stringExpr(tok, stringTok);
            }
            case ANS -> {
                Token charTok = expect(List.of(TokTyp.CHAR));
                yield charExpr(charTok);
            }
            case COMP -> compExpr(tok);
            default -> throw parseException(tok);
        };
    }

    private TreeNode compExpr(Token compTok) {
        TreeNode node = base.getSumExpr().sumExpr(compTok);
        return ComputeNode.builder()
            .token(compTok)
            .args(node)
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
