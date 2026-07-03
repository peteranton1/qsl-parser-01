package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;

public abstract sealed class TreeNode
    permits InfixNode, MultiNode, TerminalNode {
    public abstract Token getToken();
}
