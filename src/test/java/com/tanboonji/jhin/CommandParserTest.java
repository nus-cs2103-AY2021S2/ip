package com.tanboonji.jhin;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.tanboonji.jhin.command.DoneCommand;
import com.tanboonji.jhin.command.EventCommand;
import com.tanboonji.jhin.command.ListCommand;
import com.tanboonji.jhin.command.ToDoCommand;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.parser.CommandParser;

class CommandParserTest {

    @Test
    void parse_toDoCommand_success() {
        String[] inputs = {"todo read book", " todo  read another book", "todo  more books "};
        for (String input: inputs) {
            try {
                assertTrue(CommandParser.parseCommand(input).getClass().isAssignableFrom(ToDoCommand.class));
            } catch (JhinException e) {
                fail();
            }
        }
    }

    @Test
    void parse_eventCommand_success() {
        String[] inputs = {"event book reading /at 01-02-2021 0000", " event  book reading /at 01/02/2021",
            "event  book reading /at 01.02.2021 2359 "};
        for (String input: inputs) {
            try {
                assertTrue(CommandParser.parseCommand(input).getClass().isAssignableFrom(EventCommand.class));
            } catch (JhinException e) {
                fail();
            }
        }
    }

    @Test
    void parse_listCommand_success() {
        String[] inputs = {"list", " list  all", "list  everything "};
        for (String input: inputs) {
            try {
                assertTrue(CommandParser.parseCommand(input).getClass().isAssignableFrom(ListCommand.class));
            } catch (JhinException e) {
                fail();
            }
        }
    }

    @Test
    void parse_doneCommand_success() {
        String[] inputs = {"done 0", " done  1", "done  2 "};
        for (String input: inputs) {
            try {
                assertTrue(CommandParser.parseCommand(input).getClass().isAssignableFrom(DoneCommand.class));
            } catch (JhinException e) {
                fail();
            }
        }
    }

    @Test
    void parse_invalidCommand_dukeException() {
        String[] inputs = {"blah", "  unknown", "invalid  "};
        for (String input: inputs) {
            try {
                CommandParser.parseCommand(input);
                fail();
            } catch (JhinException e) {
                // Exception caught, pass test case.
            }
        }
    }

    @Test
    void parse_invalidToDoCommand_dukeException() {
        String[] inputs = {"todo", "  todo", "todo  "};
        for (String input: inputs) {
            try {
                CommandParser.parseCommand(input);
                fail();
            } catch (JhinException e) {
                // Exception caught, pass test case.
            }
        }
    }

    @Test
    void parse_invalidEventCommand_dukeException() {
        String[] inputs = {"event", " event  book reading", "event  book reading /by 01.01.2021 0000 "};
        for (String input: inputs) {
            try {
                CommandParser.parseCommand(input);
                fail();
            } catch (JhinException e) {
                // Exception caught, pass test case.
            }
        }
    }

    @Test
    void parse_invalidDoneCommand_dukeException() {
        String[] inputs = {"done", " done  one", "done  two "};
        for (String input: inputs) {
            try {
                CommandParser.parseCommand(input);
                fail();
            } catch (JhinException e) {
                // Exception caught, pass test case.
            }
        }
    }
}
