package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.exception.DukeInsufficientParametersException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidParametersException;
import duke.exception.DukeMissingFlagException;
import duke.exception.DukeUnknownCommandException;


public class ParserTest {

    /*
     * Valid Commands
     */
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
        Command c = Parser.parse("done 1");
        assertTrue(c instanceof DoneCommand);
        assertFalse(c.isExit());
    }

    @Test
    public void testDeleteCommand() throws DukeException {
        Command c = Parser.parse("delete 1");
        assertTrue(c instanceof DeleteCommand);
        assertFalse(c.isExit());
    }

    @Test
    public void testToDoCommand() throws DukeException {
        Command c = Parser.parse("todo this");
        assertTrue(c instanceof ToDoCommand);
        assertFalse(c.isExit());
    }

    @Test
    public void testEventCommand() throws DukeException {
        Command c = Parser.parse("event this /at 12121999");
        assertTrue(c instanceof EventCommand);
        assertFalse(c.isExit());
    }

    @Test
    public void testDeadlineCommand() throws DukeException {
        Command c = Parser.parse("deadline this /by 12121999");
        assertTrue(c instanceof DeadlineCommand);
        assertFalse(c.isExit());
    }

    /*
     * Command Verification Check
     */
    @Test
    public void testVerifyCommand() {
        String c = "hello";
        assertThrows(DukeUnknownCommandException.class, () -> Parser.parse(c));
    }

    // Zero-param commands have no special exceptions to be tested

    /*
     * Command Specific Checks: DeleteCommand
     */
    @Test
    public void deleteInvalidParamCheck() {
        String c = "delete abc";
        assertThrows(DukeInvalidParametersException.class, () -> Parser.parse(c));
    }

    @Test
    public void deleteInsufficientParamCheck() {
        String c = "delete";
        assertThrows(DukeInsufficientParametersException.class, () -> Parser.parse(c));
    }

    /*
     * Command Specific Checks: DoneCommand
     */
    @Test
    public void doneInvalidParamCheck() {
        String c = "done abc";
        assertThrows(DukeInvalidParametersException.class, () -> Parser.parse(c));
    }

    @Test
    public void doneInsufficientParamCheck() {
        String c = "done";
        assertThrows(DukeInsufficientParametersException.class, () -> Parser.parse(c));
    }

    /*
     * Command Specific Checks: ToDoCommand
     */
    @Test
    public void toDoParamCheck() {
        String c = "todo";
        assertThrows(DukeInsufficientParametersException.class, () -> Parser.parse(c));
    }

    /*
     * Command Specific Checks: DeadlineCommand
     */
    @Test
    public void deadlineParamCheck() {
        String c = "deadline";
        assertThrows(DukeInsufficientParametersException.class, () -> Parser.parse(c));
    }

    @Test
    public void deadlineMissingFlagCheck() {
        String c = "deadline testing";
        assertThrows(DukeMissingFlagException.class, () -> Parser.parse(c));
    }

    @Test
    public void deadlineWrongDateCheck() {
        String c = "deadline testing /by 123";
        assertThrows(DukeInvalidDateException.class, () -> Parser.parse(c));
    }

    /*
     * Command Specific Checks: EventCommand
     */
    @Test
    public void eventParamCheck() {
        String c = "event";
        assertThrows(DukeInsufficientParametersException.class, () -> Parser.parse(c));
    }

    @Test
    public void eventMissingFlagCheck() {
        String c = "event testing";
        assertThrows(DukeMissingFlagException.class, () -> Parser.parse(c));
    }

    @Test
    public void eventWrongDateCheck() {
        String c = "event testing /at 123";
        assertThrows(DukeInvalidDateException.class, () -> Parser.parse(c));
    }
}
