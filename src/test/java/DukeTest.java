import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testInvalidTodoCommand() throws InvalidCommandException {
        Duke test = new Duke();
        assertEquals(test.parser.readCommand("Todo"),
                "\u2639 OOPS!!! The description of a todo cannot be empty...");
    }

    @Test
    public void testInvalidDeadlineCommand() throws InvalidCommandException {
        Duke test = new Duke();
        assertEquals(test.parser.readCommand("Deadline"),
                "\u2639 OOPS!!! The description of a deadline cannot be empty...");

    }

    @Test void testInvalidDeadlineDescription() throws InvalidCommandException {
        Duke test = new Duke();
        assertEquals(test.parser.readCommand("Deadline read book"),
                "\u2639 OOPS!! format for deadline: deadline (task) /by yyyy-MM-dd");

    }
}
