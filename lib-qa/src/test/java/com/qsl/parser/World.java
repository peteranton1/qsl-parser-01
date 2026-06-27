package com.qsl.parser;

public class World {
    int dummyInt;
    String content;
    String expected;
    String actual;

    public World() {
        this.dummyInt = 0;
        this.content = "";
        this.expected = "";
        this.actual = "";
    }

    public void log(String msg) {
        System.out.println(msg);
    }
}
