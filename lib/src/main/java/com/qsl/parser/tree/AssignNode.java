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
public final class AssignNode extends TreeNode {
    private Token token;
    private List<TreeNode> children;

    @Override
    public String toString() {
        return "[" + token +
            ", =" + children +
            "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AssignNode that = (AssignNode) o;
        return Objects.equals(token, that.token) && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, children);
    }
}
