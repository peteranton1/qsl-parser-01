package com.qsl.parser.par;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IdentGeneratorTest {

    @Test
    void generateIdent_whenFirst5Entries() {
        IdentGenerator underTest =
            new IdentGenerator("test", 10);
        List<String> expectedList = List.of(
            "test0000000001",
            "test0000000002",
            "test0000000003",
            "test0000000004",
            "test0000000005"
        );
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(expectedList.get(i),
                underTest.generateIdent());
        }
    }
}