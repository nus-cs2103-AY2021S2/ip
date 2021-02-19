package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StatementTest {
    @Test
    public void parse_correctCommand_success() throws Exception{
        assertEquals(new ListCommand(), new Statement("list").parse());

        assertEquals(new ExitCommand(), new Statement("bye").parse());

        assertEquals(new DoneCommand("1"), new Statement("done 1").parse());

        assertEquals(new DeleteCommand("1"), new Statement("delete 1").parse());

        assertEquals(new AddCommand("todo", "buy books", null, null ),
                new Statement("todo buy books").parse());
    }

    @Test
    public void parse_unrecognisedCommand_throwException() {
        try {
            assertEquals("", new Statement("blah").parse());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, but I don't know what blah means. :(", e.getMessage());
        }
    }
}
