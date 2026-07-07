package com.qsl.parser.par;

import com.qsl.parser.lex.Lexer;
import com.qsl.parser.lex.TokTyp;
import com.qsl.parser.lex.Token;
import com.qsl.parser.tree.MultiNode;
import com.qsl.parser.tree.TreeNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class Parser extends ParseBase {

    public Parser(Lexer lexer) {
        super(lexer, new ParseObjects(lexer));
    }

    public TreeNode prog() {
        return base.getStmts().stmts();
    }
}
