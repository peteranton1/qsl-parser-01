package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;

public abstract sealed class TreeNode
    permits AssignNode,
    ComputeNode,
    ExecNode,
    IdentNode,
    InfixNode,
    MultiNode,
    ScriptNode,
    TerminalNode
    {
    public abstract Token getToken();
}
