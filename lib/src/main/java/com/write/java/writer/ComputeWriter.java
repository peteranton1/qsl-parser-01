package com.write.java.writer;

import com.qsl.parser.tree.ComputeNode;

public record ComputeWriter(WriterObjects base) {

    String writeCompute(ComputeNode compute, int indent) {
        return base.getIndenter().nlStr(indent)
            + compute.toString();
    }
}
