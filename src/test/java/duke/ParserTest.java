package duke;

import duke.command.Command;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class ParserTest {
    @Test
    public void testBasicCommandTypes() throws DukeException {
        Command c1 = Parser.parse("help");
        assertTrue(c1 instanceof HelpCommand);
        Command c2 = Parser.parse("list");
        assertTrue(c2 instanceof ListCommand);
        Command c3 = Parser.parse("bye");
        assertTrue(c3 instanceof ByeCommand);
    }
}