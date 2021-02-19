import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;

public class ParserTest {

    @Test
    public void parser_unrecognisedCommand_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> new Parser(new Storage()).process("hellooo!"));
    }

    @Test
    public void parser_missingDescription_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> new Parser(new Storage()).process("event "));
    }
}
