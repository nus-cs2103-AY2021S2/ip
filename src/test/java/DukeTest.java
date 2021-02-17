import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void parseTodo_noDescription_exceptionThrown() {
        try {
            Parser.parseCommand("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! The description of todo cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parseDeadline_noDescription_exceptionThrown() {
        try {
            Parser.parseCommand("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! The description of deadline cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parseEvent_noDescription_exceptionThrown() {
        try {
            Parser.parseCommand("event");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! The description of event cannot be empty!", e.getMessage());
        }
    }
}
