package util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void getCommand() {
    }

    @Test
    void getArgMap1() {
        HashMap<String, String> testMap = new HashMap<>();
        assertEquals(testMap, Parser.getArgMap("todo "));
    }

    @Test
    void getArgMap2() {
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("desc", "");
        testMap.put("at", "123 ");
        testMap.put("done", "");
        assertEquals(testMap, Parser.getArgMap("todo /at 123 /done "));
    }
}