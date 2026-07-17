package com.write.java.writer;

import com.qsl.parser.tree.ScriptNode;

public record ScriptWriter(WriterObjects base) {

    String writeScript(ScriptNode script, int indent) {
        return base.getIndenter().nlStr(indent)
            + script.toString();
    }
}
