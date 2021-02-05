package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Represents a test driver for <code>Parser</code>.
 */
public class ParserTest {

    /**
     * Tests how Parser parses valid command input.
     * @throws DukeException If invalid command is given.
     */
    @Test
    public void testParse_success() throws DukeException{
        Command c1 = Parser.parse("bye");
        Command c2 = Parser.parse("list");
        assertTrue(c1 instanceof ExitCommand);
        assertTrue(c2 instanceof ListCommand);
    }

    /**
     * Tests how Parser parses invalid command input.
     */
    @Test
    public void testParse_exceptionThrown(){
        try {
            Parser.parse("delete");
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals(":< OOPS!!! The task index is missing.", e.toString());
        }

        try {
            Parser.parse("todo");
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals(":< OOPS!!! The task description is missing.", e.toString());
        }
    }
}
