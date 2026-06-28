package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public final class InfixNode extends TreeNode {
    private Token token;
    private TreeNode left;
    private TreeNode right;
}
