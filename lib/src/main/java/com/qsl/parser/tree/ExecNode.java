package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public final class ExecNode extends TreeNode {
    private Token token;
    private List<TreeNode> children;

    @Override
    public String toString() {
        return "['" + token +
            "'=[" + children +
            ']';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExecNode multiNode = (ExecNode) o;
        return Objects.equals(token, multiNode.token) && Objects.equals(children, multiNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, children);
    }
}
