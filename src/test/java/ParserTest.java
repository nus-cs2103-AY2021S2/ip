import duke.commands.AddToDoCommand;
import duke.commands.InvalidCommand;
import duke.parser.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parser_addToDoCmdEmptyDescription_returnsInvalidCommand() {
        Parser p = new Parser();

        assertTrue(p.parseCmd("todo") instanceof InvalidCommand);
    }

    @Test
    public void parser_addToDoCmdProper_returnsAddToDoCmd() {
        Parser p = new Parser();

        assertTrue(p.parseCmd("todo something big") instanceof AddToDoCommand);
    }
}
