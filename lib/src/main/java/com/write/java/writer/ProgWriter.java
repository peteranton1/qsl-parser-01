package com.write.java.writer;

import com.qsl.parser.tree.*;

public class ProgWriter {

    private final WriterObjects base;

    public ProgWriter() {
        base = new WriterObjects(this);
    }

    public String writeProg(TreeNode root, int indent) {
        return switch(root) {
            case AssignNode assign ->
                base.getAssign().writeAssign(assign, indent);
            case BlockNode block ->
                base.getBlock().writeBlock(block, indent);
            case ComputeNode compute ->
                base.getCompute().writeCompute(compute, indent);
            case ExecNode exec ->
                base.getExec().writeExec(exec, indent);
            case IdentNode ident ->
                base.getIdent().writeIdent(ident, indent);
            case InfixNode infix ->
                base.getInfix().writeInfix(infix, indent);
            case MultiNode multi ->
                base.getMulti().writeMulti(multi, indent);
            case ScriptNode script ->
                base.getScript().writeScript(script, indent);
            case TerminalNode terminal ->
                base.getTerminal().writeTerminal(terminal, indent);
        };
    }
}
