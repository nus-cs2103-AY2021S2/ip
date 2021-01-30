package com.lirc572.ip;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests Parser class.
 */
public class ParserTest {

    /**
     * Stringifies an array of Strings
     *
     * @param stringArray The String array to be stringified
     * @return The string representation of the array
     */
    private String stringifyStringArray(String[] stringArray) {
        StringBuilder result = new StringBuilder("{ ");
        for (String element: stringArray) {
            result.append(element).append(", ");
        }
        return result.substring(0, result.length() - 2) + " }";
    }

    /**
     * Tests stringifyStringArray() method.
     */
    @Test
    public void testStringifyStringArray() {
        final String[][] inputs = new String[][]{
            {"abc"},
            {"abc", "def"},
            {"abc", "def", "geh"}
        };
        final String[] outputs = {
            "{ abc }",
            "{ abc, def }",
            "{ abc, def, geh }"
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(outputs[i], stringifyStringArray(inputs[i]));
        }
    }

    /**
     * Tests Parser.tokenizeCommand() method.
     */
    @Test
    public void testTokenizeCommand() {
        final String[] inputs = {
            "",
            "abc",
            "abc def",
            "abc def geh",
            "todo  \"New todo\"",
            "deadline \"submit homework\" /by 12:00",
            "event say_hi /at \"Sunday morning\"",
            "todo  'New todo'",
            "deadline 'submit homework' /by 12:00",
            "event say_hi /at 'Sunday morning'",
            "deadline \"CS2103T IP\" /at 'Wednesday 12:59'",
            "deadline \"CS2103T IP\" /at \"Wednesday 12:59\"",
            "todo  \"New 'todo'\"",
            "todo  \"have lunch at Prince George's Park\"",
            "todo  'tell Bob: \"Nice to meet you!\"'",
        };
        final String[][] outputs = new String[][]{
            {},
            {"abc"},
            {"abc", "def"},
            {"abc", "def", "geh"},
            {"todo", "New todo"},
            {"deadline", "submit homework", "/by", "12:00"},
            {"event", "say_hi", "/at", "Sunday morning"},
            {"todo", "New todo"},
            {"deadline", "submit homework", "/by", "12:00"},
            {"event", "say_hi", "/at", "Sunday morning"},
            {"deadline", "CS2103T IP", "/at", "Wednesday 12:59"},
            {"deadline", "CS2103T IP", "/at", "Wednesday 12:59"},
            {"todo", "New 'todo'"},
            {"todo", "have lunch at Prince George's Park"},
            {"todo", "tell Bob: \"Nice to meet you!\""},
        };

        for (int i = 0; i < inputs.length; i++) {
            assertArrayEquals(outputs[i], Parser.tokenizeCommand(inputs[i]));
        }
    }
}
