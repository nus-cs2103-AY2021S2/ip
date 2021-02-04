import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void doneCommandTest() {
        assertEquals(Parser.parse("reset", new DukeList()), "reset");
    }

    @Test
    public void byeCommandTest() {
        assertEquals(Parser.parse("bye", new DukeList()), "bye");
    }

    @Test
    public void deadLineCommandTest() {
        assertEquals(Parser.parse("bysd", new DukeList()), "unknown");
    }
}