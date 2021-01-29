package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parseTest() throws DukeException {
        assertEquals("todo test1 ", Parser.parse("todo test1").getTaskInfo());
        assertEquals("deadline hello 2018-10-01", Parser.parse("deadline hello /by 2018-10-01").getTaskInfo());
        assertEquals("event hello 2018-10-01", Parser.parse("event hello /at 2018-10-01").getTaskInfo());
        assertEquals("list  ", Parser.parse("list").getTaskInfo());
        assertEquals("bye  ", Parser.parse("bye").getTaskInfo());
        assertEquals("done 1 ", Parser.parse("done 1").getTaskInfo());
    }
}
