package duke;

import static duke.model.exception.DukeException.NoArgumentOrWrongFormatException.WRONG_FORMAT_MSG;
import static duke.model.exception.DukeException.UnknownCommandException.UNKNOWN_COMMAND_MSG;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.model.Command;

public class ParserTest {
    @Test
    public void parseWithFormatCheck_checkCommand() {
        assertEquals(Command.DEADLINE, ModelForTest.PARSER_DEADLINE.getCommand());
        assertEquals(Command.DONE, ModelForTest.PARSER_DONE.getCommand());
        assertEquals(Command.EVENT, ModelForTest.PARSER_EVENT.getCommand());
        assertEquals(Command.ERROR, ModelForTest.PARSER_ERROR.getCommand());
        assertEquals(Command.LIST, ModelForTest.PARSER_LIST.getCommand());
        assertEquals(Command.TODO, ModelForTest.PARSER_TODO.getCommand());
        assertEquals(Command.TAG, ModelForTest.PARSER_TAG.getCommand());
        assertEquals(Command.ERROR, ModelForTest.PARSER_WRONG_DEADLINE.getCommand());
        assertEquals(Command.ERROR, ModelForTest.PARSER_WRONG_EVENT.getCommand());
    }

    @Test
    public void parseWithFormatCheck_checkArgument() {
        assertEquals("return book", ModelForTest.PARSER_DEADLINE.getArgument());
        assertEquals("1", ModelForTest.PARSER_DONE.getArgument());
        assertEquals("team meeting", ModelForTest.PARSER_EVENT.getArgument());
        assertEquals("submit ip jar", ModelForTest.PARSER_TODO.getArgument());
        assertEquals(null, ModelForTest.PARSER_LIST.getArgument());
        assertEquals(UNKNOWN_COMMAND_MSG, ModelForTest.PARSER_ERROR.getArgument());
        assertEquals("1", ModelForTest.PARSER_TAG.getArgument());
        assertEquals(String.format(WRONG_FORMAT_MSG, "deadline"), ModelForTest.PARSER_WRONG_DEADLINE.getArgument());
        assertEquals(String.format(WRONG_FORMAT_MSG, "event"), ModelForTest.PARSER_WRONG_EVENT.getArgument());
    }

    @Test
    public void parseWithFormatCheck_checkOptionalArgument() {
        assertEquals("Sunday", ModelForTest.PARSER_DEADLINE.getOptionalArgument());
        assertEquals(null, ModelForTest.PARSER_DONE.getOptionalArgument());
        assertEquals("2:00pm", ModelForTest.PARSER_EVENT.getOptionalArgument());
        assertEquals(null, ModelForTest.PARSER_TODO.getOptionalArgument());
        assertEquals(null, ModelForTest.PARSER_LIST.getOptionalArgument());
        assertEquals(null, ModelForTest.PARSER_ERROR.getOptionalArgument());
        assertEquals(null, ModelForTest.PARSER_WRONG_DEADLINE.getOptionalArgument());
        assertEquals(null, ModelForTest.PARSER_WRONG_EVENT.getOptionalArgument());
    }
}
