package duke.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.component.Parser;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.AddCommand;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

public class ParserTest {
    @Test
    public void parse_validCommand_success() throws Exception {
        assertEquals(new ExitCommand(), Parser.parse("bye"));
        assertEquals(new ListCommand(), Parser.parse("list"));
        assertEquals(new DoneCommand(0), Parser.parse("done 1"));
        assertEquals(new DeleteCommand(0), Parser.parse("delete 1"));
        assertEquals(new AddCommand(new ToDo("buy book")), Parser.parse("todo buy book"));
        assertEquals(new AddCommand(new Deadline("return book", "2020-06-07")), Parser.parse("deadline return book "
                + "/by 2020-06-07"));
        assertEquals(new AddCommand(new Event("library meetup", "2020-08-27")), Parser.parse("deadline return book "
                + "/at 2020-08-27"));
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
