package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseCommand_oneWordCommand_emptySecondElement() {
        String[] actual = Parser.parseCommand("test");
        assertEquals("test", actual[0]);
        assertEquals("", actual[1]);
    }

    @Test
    public void parseCommand_oneWordWithSpaceCommand_emptySecondElement() {
        String[] actual = Parser.parseCommand("test ");
        assertEquals("test", actual[0]);
        assertEquals("", actual[1]);
    }

    @Test
    public void parseCommand_manyCommand_twoElementArray() {
        String[] actual = Parser.parseCommand("test test2 test3 test4");
        assertEquals("test", actual[0]);
        assertEquals("test2 test3 test4", actual[1]);
    }
}
