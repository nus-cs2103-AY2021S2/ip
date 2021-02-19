import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parser_unrecognisedCommand_dukeExceptionThrown() {
        assertThrows(DukeException.class,
                () -> new Parser(new Storage()).process("hellooo!"));
    }

    @Test
    public void parser_missingDescription_dukeExceptionThrown() {
        assertThrows(DukeException.class,
                () -> new Parser(new Storage()).process("event "));
    }
}