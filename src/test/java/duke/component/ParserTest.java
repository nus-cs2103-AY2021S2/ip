package duke.component;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_validCommand_success() throws Exception {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("todo buy book") instanceof AddCommand);
        assertTrue(Parser.parse("deadline return book /by 2020-06-07") instanceof AddCommand);
        assertTrue(Parser.parse("event library meetup /at 2020-08-27") instanceof AddCommand);
        assertTrue(Parser.parse("find book") instanceof FindCommand);
    }

    @Test
    public void parse_emptyTodo_exceptionThrown() {
        try {
            assertEquals(null, Parser.parse("todo"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a(n) todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeadline_exceptionThrown() {
        try {
            assertEquals(null, Parser.parse("deadline /by"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a(n) deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyEvent_exceptionThrown() {
        try {
            assertEquals(null, Parser.parse("event /at"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a(n) event cannot be empty.", e.getMessage());
        }
    }
}
