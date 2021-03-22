package seashell.parser;

import org.junit.jupiter.api.Test;
import seashell.command.*;
import seashell.exception.SeashellException;
import seashell.task.TaskType;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_addTodoCommand_Successful() throws SeashellException {
        Command addCommand = new AddCommand(TaskType.TODO, "read book");

        assertEquals(Parser.parse("todo read book"), addCommand);
    }

    @Test
    public void parse_addDeadlineCommand_Successful() throws SeashellException {
        Command addCommand = new AddCommand(TaskType.DEADLINE, "read book", "2020-03-07");

        assertEquals(Parser.parse("deadline read book /by 2020-03-07"), addCommand);
    }

    @Test
    public void parse_addEventCommand_Successful() throws SeashellException {
        Command addCommand = new AddCommand(TaskType.EVENT, "read book", "2020-08-22");

        assertEquals(Parser.parse("event read book /at 2020-08-22"), addCommand);
    }

    @Test
    public void parse_clearCommand_Successful() throws SeashellException {
        Command clearCommand = new ClearCommand();

        assertEquals(Parser.parse("clear"), clearCommand);
    }

    @Test
    public void parse_listCommand_Successful() throws SeashellException {
        Command listCommand = new ListCommand();

        assertEquals(Parser.parse("list"), listCommand);
    }

    @Test
    public void parse_helpCommand_Successful() throws SeashellException {
        Command helpCommand = new HelpCommand();

        assertEquals(Parser.parse("help"), helpCommand);
    }

    @Test
    public void parse_findCommand_Successful() throws SeashellException {
        Command findCommand = new FindCommand("testing");

        assertEquals(Parser.parse("find testing"), findCommand);
    }

    @Test
    public void parse_deleteCommand_Successful() throws SeashellException {
        Command deleteCommand = new DeleteCommand(3);

        assertEquals(Parser.parse("delete 3"), deleteCommand);
    }

    @Test
    public void parse_doneCommand_Successful() throws SeashellException {
        Command doneCommand = new DoneCommand(1);

        assertEquals(Parser.parse("done 1"), doneCommand);
    }
}