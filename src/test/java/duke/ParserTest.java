package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;

public class ParserTest {
    @Test
    public void testExitCommand() throws DukeException {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
        assertTrue(c.isExit());
    }

    @Test
    public void testListCommand() throws DukeException {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
        assertFalse(c.isExit());
    }

    @Test
    public void testDoneCommand() throws DukeException {
        Command c = Parser.parse("done 5");
        assertTrue(c instanceof DoneCommand);
        assertFalse(c.isExit());
    }

    @Test
    public void testEmptyDoneCommand() throws DukeException {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("done");
        });

        String expectedMessage = "I'm sorry, but done needs the index of a Task.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testUnknownExtendedCommand() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("doned");
        });

        String expectedMessage = "I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void testInvalidDoneCommand() throws DukeException {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("done a");
        });

        String expectedMessage = "The index of the task needs to be an integer.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
