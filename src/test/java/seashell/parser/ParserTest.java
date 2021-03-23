package seashell.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seashell.command.AddCommand;
import seashell.command.ClearCommand;
import seashell.command.Command;
import seashell.command.DeleteCommand;
import seashell.command.DoneCommand;
import seashell.command.FindCommand;
import seashell.command.HelpCommand;
import seashell.command.ListCommand;
import seashell.exception.SeashellException;
import seashell.task.TaskType;


public class ParserTest {

    @Test
    public void parse_addTodo_successful() throws SeashellException {
        Command addCommand = new AddCommand(TaskType.TODO, "read book");

        assertEquals(Parser.parse("todo read book"), addCommand);
    }

    @Test
    public void parse_addDeadline_successful() throws SeashellException {
        Command addCommand = new AddCommand(TaskType.DEADLINE, "read book", "2020-03-07");

        assertEquals(Parser.parse("deadline read book /by 2020-03-07"), addCommand);
    }

    @Test
    public void parse_addEvent_successful() throws SeashellException {
        Command addCommand = new AddCommand(TaskType.EVENT, "read book", "2020-08-22");

        assertEquals(Parser.parse("event read book /at 2020-08-22"), addCommand);
    }

    @Test
    public void parse_clearCommand_successful() throws SeashellException {
        Command clearCommand = new ClearCommand();

        assertEquals(Parser.parse("clear"), clearCommand);
    }

    @Test
    public void parse_listCommand_successful() throws SeashellException {
        Command listCommand = new ListCommand();

        assertEquals(Parser.parse("list"), listCommand);
    }

    @Test
    public void parse_helpCommand_successful() throws SeashellException {
        Command helpCommand = new HelpCommand();

        assertEquals(Parser.parse("help"), helpCommand);
    }

    @Test
    public void parse_findCommand_successful() throws SeashellException {
        Command findCommand = new FindCommand("testing");

        assertEquals(Parser.parse("find testing"), findCommand);
    }

    @Test
    public void parse_deleteCommand_successful() throws SeashellException {
        Command deleteCommand = new DeleteCommand(3);

        assertEquals(Parser.parse("delete 3"), deleteCommand);
    }

    @Test
    public void parse_doneCommand_successful() throws SeashellException {
        Command doneCommand = new DoneCommand(1);

        assertEquals(Parser.parse("done 1"), doneCommand);
    }
}
