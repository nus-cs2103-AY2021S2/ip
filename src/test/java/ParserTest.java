import timmy.Commands.*;
import timmy.Exceptions.DukeException;
import timmy.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser p = new Parser();

    @Test
    public void testParse() throws DukeException {
        Assertions.assertTrue(p.parse("list") instanceof PrintListCommand);
        Assertions.assertTrue(p.parse("todo H run") instanceof AddCommand);
        Assertions.assertTrue(p.parse("done 1") instanceof DoneCommand);
        Assertions.assertTrue(p.parse("delete 1") instanceof DeleteCommand);
        Assertions.assertTrue(p.parse("deadline L run /by 1999-12-19 23:24") instanceof AddCommand);
        Assertions.assertTrue(p.parse("event M run /at 1999-12-19 23:21") instanceof AddCommand);
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
