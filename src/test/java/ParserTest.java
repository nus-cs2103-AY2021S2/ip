import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    Parser p = new Parser();

    @Test
    public void testParse() throws DukeException {
        assertTrue(p.parse("list") instanceof PrintListCommand);
        assertTrue(p.parse("todo run") instanceof AddCommand);
        assertTrue(p.parse("done 1") instanceof DoneCommand);
        assertTrue(p.parse("delete 1") instanceof DeleteCommand);
        assertTrue(p.parse("deadline run /by 1999-12-19 23:24") instanceof AddCommand);
        assertTrue(p.parse("event run /at 1999-12-19 23:21") instanceof AddCommand);
        assertTrue(p.parse("bye") instanceof ExitCommand);
        try {
            p.parse("asd");
        } catch (Exception e) {
            assertEquals("Sorry, I don't know what that means...", e.getMessage());
        }
        try {
            p.parse("todo");
            p.parse("deadline");
            p.parse("event");
        } catch (Exception e) {
            assertEquals("Sorry, nothing was written after the command so I am unable to process...", e.getMessage());
        }
    }
}
