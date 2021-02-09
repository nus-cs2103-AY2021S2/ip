import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    void parse_validInput_success() throws NoSuchCommandException, IncompleteCommandException {
        assertEquals(new ExitCommand(), Parser.parse("bye"));
        assertEquals(new ListCommand(), Parser.parse("list"));
        assertEquals(new DoneCommand(1), Parser.parse("done 1"));
        assertEquals(new DeleteCommand(1), Parser.parse("delete 1"));
        assertEquals(new FindCommand("hello"), Parser.parse("find hello"));
        assertEquals(new ToDoCommand("homework"), Parser.parse("todo homework"));
        assertEquals(new EventCommand("concert", "2020-12-09"),
                Parser.parse("event concert /at 2020-12-09"));
        assertEquals(new DeadlineCommand("tutorial", "2021-01-01"),
                Parser.parse("deadline tutorial /by 2021-01-01"));
    }

    @Test
    void parse_invalidInput_incompleteCommandExceptionThrown() {
        try {
            assertEquals("hello", Parser.parse("todo"));
            fail();
        } catch (Exception e) {
            assertEquals("The description cannot be empty.", e.getMessage());
        }
    }

    @Test
    void parse_invalidInput_noSuchCommandExceptionThrown() {
        try {
            assertEquals("hello", Parser.parse("hehe"));
            fail();
        } catch (Exception e) {
            assertEquals("I'm sorry, but I don't know what that means :(", e.getMessage());
        }
    }
}
