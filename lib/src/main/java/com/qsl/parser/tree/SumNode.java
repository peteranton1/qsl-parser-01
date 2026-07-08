package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public final class SumNode extends TreeNode {
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
        SumNode sumNode = (SumNode) o;
        return Objects.equals(token, sumNode.token) && Objects.equals(args, sumNode.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, args);
    }
}
