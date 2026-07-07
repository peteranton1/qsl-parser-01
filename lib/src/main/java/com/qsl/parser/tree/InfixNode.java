package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public final class InfixNode extends TreeNode {
    private Token token;
    private TreeNode left;
    private TreeNode right;

    @Override
    public String toString() {
        return "['" + token +
            "'=[" + left + ", " + right + "]]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InfixNode infixNode = (InfixNode) o;
        return Objects.equals(token, infixNode.token) && Objects.equals(left, infixNode.left) && Objects.equals(right, infixNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, left, right);
    }
}
