package com.write.java.writer;

import com.qsl.parser.tree.ExecNode;

public record ExecWriter(WriterObjects base) {

    String writeExec(ExecNode exec, int indent) {
        return base.getIndenter().nlStr(indent)
            + exec.toString();
    }
}
