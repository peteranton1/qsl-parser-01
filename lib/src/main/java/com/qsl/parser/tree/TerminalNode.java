package com.qsl.parser.tree;

import com.qsl.parser.lex.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public final class TerminalNode extends TreeNode {
    private Token token;

    @Override
    public String toString() {
        return token.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TerminalNode that = (TerminalNode) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }
}
