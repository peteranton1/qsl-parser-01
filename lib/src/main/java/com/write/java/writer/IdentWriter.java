package com.write.java.writer;

import com.qsl.parser.tree.IdentNode;

public record IdentWriter(WriterObjects base) {

    String writeIdent(IdentNode ident, int indent) {
        return base.getIndenter().nlStr(indent)
            + ident.toString();
    }
}
