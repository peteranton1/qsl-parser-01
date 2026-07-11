package com.qsl.parser.print;

import com.qsl.parser.tree.*;

import java.util.List;

public class TreePrinter {
    public TreePrinter() {
        // constructor
    }

    public String printTree(TreeNode root, Fmt fmt) {
        StringBuilder sb = new StringBuilder();
        int indent = 0;

        sb.append(printOfType(root, indent + 1, fmt));
        return sb.toString();
    }

    private String printOfType(TreeNode node, int indent, Fmt fmt) {

        if(node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String indentStr = (Fmt.NL.equals(fmt) ?
            fmt.getSep() + " ".repeat(indent) :
            "");

        String name = switch (node) {
            case AssignNode _ -> "Agn";
            case BlockNode _ -> "Blo";
            case ComputeNode _ -> "Com";
            case ExecNode _ -> "Exe";
            case IdentNode _ -> "Id";
            case InfixNode _ -> "InF";
            case MultiNode _ -> "Mul";
            case ScriptNode _ -> "Scr";
            case TerminalNode _ -> "Ter";
        };

        sb
            .append(indentStr)
            .append(name)
            .append("(")
            .append(node.getToken())
            .append(")")
        ;
        // print children
        switch (node) {
            case AssignNode assign ->
                printChildren(assign.getChildren(), sb, indent, fmt);
            case BlockNode block ->
                printChildren(block.getChildren(), sb, indent, fmt);
            case ComputeNode compute ->
                printChildren(compute.getChildren(), sb, indent, fmt);
            case ExecNode exec ->
                printChildren(exec.getChildren(), sb, indent, fmt);
            case IdentNode ident ->
                sb.append(printOfType(ident.getExpr(), indent + 1, fmt));
            case InfixNode infix -> {
                sb.append(printOfType(infix.getLeft(), indent + 1, fmt));
                sb.append(printOfType(infix.getRight(), indent + 1, fmt));
            }
            case MultiNode multi ->
                printChildren(multi.getChildren(), sb, indent, fmt);
            case ScriptNode script ->
                printChildren(script.getChildren(), sb, indent, fmt);
            case TerminalNode _ -> sb.append(".");
        }

        return sb.toString();
    }

    private void printChildren(List<TreeNode> multi, StringBuilder sb, int indent, Fmt fmt) {
        for (TreeNode child : multi) {
            sb.append(printOfType(child, indent + 1, fmt));
        }
    }
}
