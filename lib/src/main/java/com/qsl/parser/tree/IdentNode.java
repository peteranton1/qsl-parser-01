package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public final class IdentNode extends TreeNode {
    private Token token;
    private TreeNode args;

    @Override
    public String toString() {
        return "['" + token +
            "'=[" + args + "]]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IdentNode identNode = (IdentNode) o;
        return Objects.equals(token, identNode.token) && Objects.equals(args, identNode.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, args);
    }
}
