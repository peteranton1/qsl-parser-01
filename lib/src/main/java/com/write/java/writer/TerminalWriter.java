package com.write.java.writer;

import com.qsl.parser.tree.TerminalNode;

public record TerminalWriter(WriterObjects base) {

    String writeTerminal(TerminalNode terminal, int indent) {
        return base.getIndenter().nlStr(indent)
                + terminal.toString();
    }
}
