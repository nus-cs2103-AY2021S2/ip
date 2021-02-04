import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        try {
            Command c = Parser.parse("aaaa");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    @Test
    public void parseCommand_unspecifiedDoneCommand_exceptionThrown() {
        try {
            Command c = Parser.parse("done");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("☹ OOPS!!! I don't know which task to mark as done."));
        }
    }


}
