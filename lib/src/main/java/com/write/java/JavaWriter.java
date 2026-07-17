package com.write.java;

import com.qsl.parser.tree.TreeNode;
import com.write.java.writer.ProgWriter;

public class JavaWriter {
    public String writeProg(TreeNode root, int indent) {
        return new ProgWriter().writeProg(root, indent);
    }
}
