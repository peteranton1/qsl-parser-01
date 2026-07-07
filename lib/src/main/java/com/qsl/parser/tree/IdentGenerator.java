package com.qsl.parser.tree;

import java.util.concurrent.atomic.AtomicLong;

public class IdentGenerator {

    private final String prefix;
    private final int genLen;
    private final int maxLen = 15;

    private final AtomicLong counter = new AtomicLong(0);

    public IdentGenerator(String prefix, int genLen) {
        this.prefix = prefix;
        if (genLen > maxLen || genLen <= 0) {
            throw new IllegalArgumentException("genLen(%d) > maxLen(%d)"
                .formatted(genLen, maxLen));
        }
        this.genLen = genLen;
    }

    public String generateIdent() {
        long serial = counter.incrementAndGet();
        String serialStr = String.format("%015d", serial);
        int strlen = serialStr.length();
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (int i = strlen - genLen; i < strlen; i++) {
            sb.append(serialStr.charAt(i));
        }
        return sb.toString();
    }

}
