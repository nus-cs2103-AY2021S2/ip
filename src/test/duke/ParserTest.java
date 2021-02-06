package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_validCommand_success() {
        assertDoesNotThrow(() -> {
            Command command = Parser.parse("todo homework");
        });
    }

    @Test
    void parse_invalidCommand_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command command = Parser.parse("blah");
        });
    }
}