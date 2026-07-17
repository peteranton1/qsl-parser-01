package com.write.java.writer;

import com.qsl.parser.tree.AssignNode;

public record AssignWriter(WriterObjects base) {

    String writeAssign(AssignNode assign, int indent) {
        return base.getIndenter().nlStr(indent)
            + assign.toString();
    }
}
