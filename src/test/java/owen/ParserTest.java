import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import owen.OwenException;
import owen.Parser;
import owen.command.Command;
import owen.command.CommandType;

public class ParserTest {
    @Test
    public void parseCommand_knownCommand_success() throws OwenException {
        Command command = Parser.parseCommand("deadline test /by 1/1/2021 1400");
        assertEquals(CommandType.DEADLINE, command.getType());
        assertEquals("test /by 1/1/2021 1400", command.getArgs());
        assertEquals("deadline test /by 1/1/2021 1400", command.getOriginal());
    }

    @Test
    public void parseCommand_unknownCommand_exception() {
        try {
            Parser.parseCommand("test");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nI'm sorry, but I don't know what that means...",
                    exception.getMessage());
        }
    }
}
