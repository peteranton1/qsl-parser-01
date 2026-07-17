package com.write.java.writer;

public class Indenter {
    public String nlStr(int indent) {
        return "\n" + " ".repeat(indent);
    }

    public String str(int indent) {
        return " ".repeat(indent);
    }
}
