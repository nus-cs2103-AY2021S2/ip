package parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.AddCommand;
import commands.ByeCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ListCommand;
import exceptions.DukeException;



public class ParserTest {
    @Test
    public void parserTest() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("todo t1") instanceof AddCommand);
        assertTrue(Parser.parse("deadline d1 /by 2020-06-02") instanceof AddCommand);
        assertTrue(Parser.parse("event e1 /at Monday 12:00 - 14:00") instanceof AddCommand);
    }
}
