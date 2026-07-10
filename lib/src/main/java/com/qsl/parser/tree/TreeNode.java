package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;

public abstract sealed class TreeNode
    permits
        InfixNode,
        MultiNode,
        TerminalNode,
        IdentNode,
        AssignNode,
        ComputeNode
    {
    public abstract Token getToken();
}
