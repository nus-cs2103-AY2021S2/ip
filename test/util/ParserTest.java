package util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getCommand() {
    }

    @Test
    void getArgMap() {
        HashMap<String, String> test1map = new HashMap<>();
        assertEquals(test1map, Parser.getArgMap("todo "));
    }
}