package duke.parser;

import duke.command.*;
import duke.exceptions.DukeCommandParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class CommandParserTest {

    private static Stream<Arguments> provideGetArguments() {
        return Stream.of(
                Arguments.of("done 3 ", "3" ),
                Arguments.of("list", "" ),
                Arguments.of("deadline something /by this date \t" , "something /by this date" )
        );
    }
    @ParameterizedTest
    @MethodSource("provideGetArguments")
    public void getArguments_correctArguments_success(String input, String expected) {
        CommandParser p = new CommandParser(input);
        String actual = null;
        try {
            actual = p.getArguments();
        } catch (DukeCommandParseException e) {
            fail();
        }
        assertEquals(actual , expected);
    }

    @ParameterizedTest
    @CsvSource({"done 3,done", "deadline something /by sometime,deadline", " todo task,todo",})
    public void getKeyWord_correctKeyword_success(String input, String expected) {
        CommandParser p = new CommandParser(input);
        String actual = null;
        try {
            actual = p.getKeyWord();
        } catch (DukeCommandParseException e) {
            fail();
        }
        assertEquals(actual , expected);
    }

    @Test
    public void getKeyWord_emptyString_throwsException() {
        try {
            CommandParser p = new CommandParser("");
            p.getKeyWord();
            fail(); // test should not reach this line.
        } catch (Exception e) {
            assertEquals("The input cannot be empty" , e.getMessage());
        }
    }

    @Test
    public void parseCommand_markTaskCmmand_success() {
        CommandParser p = new CommandParser("done 3");
        try {
            boolean expected = p.parseCommand() instanceof MarkTaskCommand;
            assertEquals(expected, true);
        } catch (DukeCommandParseException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_listCommand_success() {
        CommandParser p = new CommandParser("list");
        try {
            boolean expected = p.parseCommand() instanceof ListCommand;
            assertEquals(expected, true);
        } catch (DukeCommandParseException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_deadlineCommand_success() {
        CommandParser p = new CommandParser("deadline something /by this date 2020-10-09 \t");
        try {
            boolean expected = p.parseCommand() instanceof AddCommand;
            assertEquals(expected, true);
        } catch (DukeCommandParseException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_todoCommand_success() {
        CommandParser p = new CommandParser("todo task");
        try {
            boolean expected = p.parseCommand() instanceof AddCommand;
            assertEquals(expected, true);
        } catch (DukeCommandParseException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_exitCommand_success() {
        CommandParser p = new CommandParser("bye");
        try {
            boolean expected = p.parseCommand() instanceof ExitCommand;
            assertEquals(expected, true);
        } catch (DukeCommandParseException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_findCommand_success() {
        CommandParser p = new CommandParser("find hello world");
        try {
            boolean expected = p.parseCommand() instanceof FindTaskCommand;
            assertEquals(expected, true);
        } catch (DukeCommandParseException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_eventCommand_success() {
        CommandParser p = new CommandParser("event something /at 2020-03-02");
        try {
            boolean expected = p.parseCommand() instanceof AddCommand;
            assertEquals(expected, true);
        } catch (DukeCommandParseException e) {
            fail();
        }
    }
}
