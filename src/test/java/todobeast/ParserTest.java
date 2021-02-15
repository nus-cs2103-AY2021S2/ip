package todobeast;

import org.junit.jupiter.api.Test;
import todobeast.commands.Command;
import todobeast.commands.ExitCommand;
import todobeast.commands.ListCommand;
import todobeast.exceptions.ToDoBeastException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testExitCommand() throws ToDoBeastException {
        Command testCommand = Parser.parse("exit");
        assertTrue(testCommand instanceof ExitCommand);
        assertTrue(testCommand.isExit());
    }

    @Test
    public void testListCommand() throws ToDoBeastException {
        Command testCommand = Parser.parse("list");
        assertTrue(testCommand instanceof ListCommand);
        assertFalse(testCommand.isExit());
    }

    @Test
    public void testInvalidCommand() {
        try {
            Command testCommand = Parser.parse("hi");

        } catch (ToDoBeastException e) {
            assertEquals("Command provided is invalid.", e.getMessage());
        }
    }

    @Test
    public void testNonIntegerTaskIndex() {
        try {
            Parser.checkTaskIndex(new String[]{"done", "a"});
        } catch (ToDoBeastException e) {
            assertEquals("Index provided is not a number.", e.getMessage());
        }
    }
}
