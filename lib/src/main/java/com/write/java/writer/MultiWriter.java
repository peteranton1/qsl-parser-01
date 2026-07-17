package com.write.java.writer;

import com.qsl.parser.tree.MultiNode;

public record MultiWriter(WriterObjects base) {

    String writeMulti(MultiNode multi, int indent) {
        return base.getIndenter().nlStr(indent)
            + multi.toString();
    }
}
