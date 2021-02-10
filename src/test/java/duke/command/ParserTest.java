package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.AddToDoCommandParseException;
import duke.exception.ListCommandParseException;
import duke.exception.UnknownCommandParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class ParserTest {

    @Test
    public void parseAsTask_emptyString_returnNull() {
        String input = "";
        assertEquals(Parser.parseAsTask(input), null);
    }

    @Test
    public void parseAsTask_invalidString_returnNull() {
        String input = "K | 1 | asdf";
        assertEquals(Parser.parseAsTask(input), null);
    }

    @Test
    public void parseAsTask_todoSuccess_returnTask() {
        String input = "T | 1 | read book";
        Task output = new ToDo("read book");
        output.markAsDone();
        assertEquals(Parser.parseAsTask(input), output);
    }

    @Test
    public void parseAsTask_deadlineSuccess_returnTask() {
        String input = "D | 0 | return book | 2000-12-20";
        Task output = Deadline.create("return book", "2000-12-20");
        assertEquals(Parser.parseAsTask(input), output);
    }

    @Test
    public void parseAsTask_eventSuccess_returnTask() {
        String input = "E | 1 | lend book | 2020-05-12 12:30:13";
        Task output = Event.create("lend book", "2020-05-12", "12:30:13");
        output.markAsDone();
        assertEquals(Parser.parseAsTask(input), output);
    }

    @Test
    public void parseCommand_invalidAddCommand_exceptionThrow() {
        String input = "todo";
        Throwable exception = assertThrows(AddToDoCommandParseException.class, () -> Parser.parseCommand(input));
        assertEquals(exception.getMessage(), "\tPlease follow this format \"todo <task>\".");
    }

    @Test
    public void parseCommand_invalidListCommand_exceptionThrow() {
        String input = "list asdf";
        Throwable exception = assertThrows(ListCommandParseException.class, () -> Parser.parseCommand(input));
        assertEquals(exception.getMessage(), "\tPlease follow this format \"list\".");
    }

    @Test
    public void parseCommand_invalidDoneCommand_exceptionThrow() {
        String input = "done asdf";
        Throwable exception = assertThrows(NumberFormatException.class, () -> Parser.parseCommand(input));
        assertEquals(exception.getMessage(), "\tOops! Please input a number.");
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrow() {
        String input = "asfasdaasfd";
        Throwable exception = assertThrows(UnknownCommandParseException.class, () -> Parser.parseCommand(input));
        assertEquals(exception.getMessage(), "\tOops! Sorry, I do not know what that means.");
    }

    @Test
    public void parseCommand_emptyCommand_exceptionThrow() {
        String input = "";
        Throwable exception = assertThrows(UnknownCommandParseException.class, () -> Parser.parseCommand(input));
        assertEquals(exception.getMessage(), "\tOops! Sorry, I do not know what that means.");
    }
}
