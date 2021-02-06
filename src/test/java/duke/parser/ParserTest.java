package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.UnknownCommandException;
import duke.utils.Command;

public class ParserTest {
    @Test
    public void parseCommand_validCommand_success() throws UnknownCommandException {
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
        String delete = "delete";
        String bye = "bye";
        String done = "done";
        String list = "list";

        assertEquals(Command.TODO, Parser.parseCommand(todo));
        assertEquals(Command.DEADLINE, Parser.parseCommand(deadline));
        assertEquals(Command.EVENT, Parser.parseCommand(event));
        assertEquals(Command.DELETE, Parser.parseCommand(delete));
        assertEquals(Command.BYE, Parser.parseCommand(bye));
        assertEquals(Command.DONE, Parser.parseCommand(done));
        assertEquals(Command.LIST, Parser.parseCommand(list));
    }
}
