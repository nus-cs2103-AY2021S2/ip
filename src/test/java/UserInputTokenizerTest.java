import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.exceptions.DukeExceptionIllegalCommand;
import duke.parser.UserInputTokenSet;
import duke.parser.UserInputTokenizer;

public class UserInputTokenizerTest {

    @Test
    void parse_validInput_command() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("command");
        assertEquals("command", tokenSet.get("/command"));
        assertEquals("", tokenSet.get("/text"));
        assertEquals("", tokenSet.get(""));
    }

    @Test
    void parse_validInput_commandText() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 text text");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("text text", tokenSet.get("/text"));
        assertEquals("", tokenSet.get(""));
    }

    @Test
    void parse_validInput_commandTextWhiteSpaces() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("  cmd1   text text   text  ");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("text text text", tokenSet.get("/text"));
        assertEquals("", tokenSet.get(""));
    }

    @Test
    void parse_validInput_commandTextSlashes() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 text text/ /text/ //text text");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("text text/ /text/ //text text", tokenSet.get("/text"));
        assertEquals("", tokenSet.get("text"));
        assertEquals("", tokenSet.get(""));
    }

    @Test
    void parse_validInput_commandTextWithOneOption() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 text text /by ");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("text text", tokenSet.get("/text"));
        assertEquals("", tokenSet.get("by"));
        assertEquals("", tokenSet.get("text"));
        assertEquals("", tokenSet.get(""));
    }

    @Test
    void parse_validInput_commandTextWithOneOptionSpecified() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 text text /by then");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("text text", tokenSet.get("/text"));
        assertEquals("then", tokenSet.get("by"));
        assertEquals("", tokenSet.get("text"));
        assertEquals("", tokenSet.get(""));
    }

    @Test
    void parse_validInput_commandTextWithManyOptionsSpecified() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 text /by then  /at  23/01/1996  morning");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("text", tokenSet.get("/text"));
        assertEquals("then", tokenSet.get("by"));
        assertEquals("23/01/1996 morning", tokenSet.get("at"));
        assertEquals("", tokenSet.get("1996"));
        assertEquals("", tokenSet.get("text"));
        assertEquals("", tokenSet.get(""));
    }

    @Test
    void parse_validInput_commandWithManyOptions() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 /by /at");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("", tokenSet.get("by"));
        assertEquals("", tokenSet.get("at"));
        assertEquals("", tokenSet.get("/text"));
    }

    @Test
    void parse_validInput_commandWithOneOptionSpecified() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 /by then");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("then", tokenSet.get("by"));
        assertEquals("", tokenSet.get("/text"));
        assertEquals("", tokenSet.get("text"));
    }

    @Test
    void parse_validInput_commandTextWithCommandOptionSpecified() throws DukeExceptionIllegalArgument {
        UserInputTokenSet tokenSet = UserInputTokenizer.parse("cmd1 hi /command cmd2 /text hey");
        assertEquals("cmd1", tokenSet.get("/command"));
        assertEquals("hi", tokenSet.get("/text"));
        assertEquals("cmd2", tokenSet.get("command"));
        assertEquals("hey", tokenSet.get("text"));
    }

    @Test
    void parse_faultyInput_noCommand() {
        assertThrows(DukeExceptionIllegalCommand.class, () -> UserInputTokenizer.parse(""));
    }
}
