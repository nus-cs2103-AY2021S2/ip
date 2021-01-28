package duke;

import Commands.*;
import Exceptions.DukeException;
import Parser.Parser;
import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Todos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void parserTest() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("todo t1") instanceof AddCommand);
        assertTrue(Parser.parse("deadline d1 /by 2020-06-02") instanceof AddCommand);
        assertTrue(Parser.parse("event e1 /at Monday 12:00 - 14:00") instanceof AddCommand);
    }

    @Test
    public void exitTest() {
        assertEquals(true, new ByeCommand().isExit());
        assertEquals(false, new ListCommand().isExit());
        assertEquals(false, new DoneCommand(0).isExit());
        assertEquals(false, new DeleteCommand(0).isExit());
        assertEquals(false, new AddCommand(new Todos("test")).isExit());
        assertEquals(false, new AddCommand(new Deadlines("test", "2020-06-02")).isExit());
        assertEquals(false, new AddCommand(new Events("test", "Monday",
                "10:00", "12:00")).isExit());
    }

}

