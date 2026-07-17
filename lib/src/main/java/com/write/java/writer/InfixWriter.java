package com.write.java.writer;

import com.qsl.parser.tree.InfixNode;

public record InfixWriter(WriterObjects base) {

    String writeInfix(InfixNode infix, int indent) {
        return base.getIndenter().nlStr(indent)
            + infix.toString();
    }
}
