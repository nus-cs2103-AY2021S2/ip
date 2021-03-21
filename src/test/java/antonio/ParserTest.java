package antonio;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import antonio.command.ByeCommand;
import antonio.command.Command;
import antonio.command.HelpCommand;
import antonio.command.ListCommand;

public class ParserTest {
    @Test
    public void testBasicCommandTypes() throws AntonioException {
        Command c1 = Parser.parse("help");
        assertTrue(c1 instanceof HelpCommand);
        Command c2 = Parser.parse("list");
        assertTrue(c2 instanceof ListCommand);
        Command c3 = Parser.parse("bye");
        assertTrue(c3 instanceof ByeCommand);
    }
}
