package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public final class MultiNode extends TreeNode {
    private Token token;
    private List<TreeNode> children;

    @Override
    public String toString() {
        return "[" +
            "=[" + children +
            ']';
    }
}
