package com.nus.duke.parser;

import static org.junit.jupiter.api.Assertions.*;

import com.nus.duke.command.Command;
import com.nus.duke.command.DeleteCommand;
import com.nus.duke.command.DoneCommand;
import com.nus.duke.command.HelpCommand;
import com.nus.duke.command.IncorrectCommand;
import com.nus.duke.command.ListCommand;
import com.nus.duke.command.TodoCommand;
import com.nus.duke.data.Todo;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandParserTest {

    private CommandParser parser;

    @BeforeEach
    public void setUp() {
        this.parser = new CommandParser();
    }

    /*
     * Tests for 0-argument commands
     */

    @Test
    public void parse_helpCommand_parsedCorrectly() {
        final String[] inputs = {"help", "help ", "help list", "help me"};
        parseAndAssertCommandType(HelpCommand.class, inputs);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() {
        final String[] inputs = {"list", "list ", "list one", "list 2", "list today"};
        parseAndAssertCommandType(ListCommand.class, inputs);
    }


    /*
     * Tests for single index argument commands
     */

    @Test
    public void parse_deleteCommandNumericArg_indexParsedCorrectly() {
        final int testIndex = 1;
        final String input = "delete " + testIndex;
        parseAndAssertCommandType(input, DeleteCommand.class);
    }

    @Test
    public void parse_deleteCommandNoArgs_errorMessage() {
        final String[] inputs = {"delete", "delete ", "delete this", "delete one"};
        parseAndAssertCommandType(IncorrectCommand.class, inputs);
    }

    @Test
    public void parse_doneCommandNumericArg_indexParsedCorrectly() {
        final int testIndex = 1;
        final String input = "done " + testIndex;
        parseAndAssertCommandType(input, DoneCommand.class);
    }

    @Test
    public void parse_doneCommandNoArgs_errorMessage() {
        final String[] inputs = {"done", "done ", "done this", "done one", "done 1 2"};
        parseAndAssertCommandType(IncorrectCommand.class, inputs);
    }

    /*
     * Tests for Todo Command Parser
     */
    @Test
    public void parse_todoCommandValidArgs_parsedCorrectly() {
        final String[] inputs = {"todo read books", "todo today", "todo done", "todo     todo"};
        parseAndAssertCommandType(TodoCommand.class, inputs);
    }

    @Test
    public void parse_todoCommandInvalidArgs_parsedCorrectly() {
        final String[] inputs = {"todo", "todo ", "todo    "};
        parseAndAssertCommandType(IncorrectCommand.class, inputs);
    }

    /*
     * Tests for Event Command Parser
     */
//    @Test
//    public void parse_eventCommandValidDateArgs_parsedCorrectly() {
//        final String prefix = "event read books /by ";
//        parseAndAssertCommandType(TodoCommand.class, inputs);
//    }
//
//    @Test
//    public void parse_eventCommandInvalidDateArgs_parsedCorrectly() {
//        final String[] inputs = {"event", "event ", "event    "};
//        parseAndAssertCommandType(IncorrectCommand.class, inputs);
//    }

    /*
     * Utility methods
     */

    private <T extends Command> T parseAndAssertCommandType(String input,
            Class<T> expectedCommandClass) {
        final Command parsedCommand = this.parser.parseCommand(input);
        assertTrue(parsedCommand.getClass().isAssignableFrom(expectedCommandClass));
        return (T) parsedCommand;
    }

    private <T extends Command> void parseAndAssertCommandType(Class<T> expectedCommandClass,
            String... inputs) {
        for (String input : inputs) {
            final Command parsedCommand = this.parser.parseCommand(input);
            assertTrue(parsedCommand.getClass().isAssignableFrom(expectedCommandClass));
        }
    }
}