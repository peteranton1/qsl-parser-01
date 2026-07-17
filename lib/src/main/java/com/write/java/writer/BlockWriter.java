package com.write.java.writer;

import com.qsl.parser.tree.BlockNode;

public record BlockWriter(WriterObjects base) {

    String writeBlock(BlockNode block, int indent) {
        return base.getIndenter().nlStr(indent)
            + block.toString();
    }
}
