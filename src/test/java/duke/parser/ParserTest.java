package duke.parser;

import duke.command.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_todoString_AddCommand() {
        assertEquals((new AddCommand()).getClass(), (Parser.parseCommand("todo")).getClass());
    }

    @Test
    public void parseCommand_eventString_AddCommand() {
        assertEquals((new AddCommand()).getClass(), (Parser.parseCommand("event")).getClass());
    }

    @Test
    public void parseCommand_deadlineString_AddCommand() {
        assertEquals((new AddCommand()).getClass(), (Parser.parseCommand("deadline")).getClass());
    }

    @Test
    public void parseCommand_doneString_DoneCommand() {
        assertEquals((new DoneCommand()).getClass(), (Parser.parseCommand("done")).getClass());
    }

    @Test
    public void parseCommand_deleteString_DeleteCommand() {
        assertEquals((new DeleteCommand()).getClass(), (Parser.parseCommand("delete")).getClass());
    }

    @Test
    public void parseCommand_listString_ListCommand() {
        assertEquals((new ListCommand()).getClass(), (Parser.parseCommand("list")).getClass());
    }

    @Test
    public void parseCommand_helpString_HelpCommand() {
        assertEquals((new HelpCommand()).getClass(), (Parser.parseCommand("help")).getClass());
    }

    @Test
    public void parseCommand_byeString_ExitCommand() {
        assertEquals((new ExitCommand()).getClass(), (Parser.parseCommand("bye")).getClass());
    }

    @Test
    public void parseCommand_randomString_InvalidCommand() {
        assertEquals((new InvalidCommand()).getClass(), (Parser.parseCommand("asdf")).getClass());
    }
}
