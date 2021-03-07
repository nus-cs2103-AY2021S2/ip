package duke.parser;

import duke.commands.InvalidCommand;
import duke.exceptions.DukeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void init() {
        parser = new Parser();
    }

    @Test
    public void parse_emptyInput_success() throws DukeException {
        final String input = "";
        assertTrue(parser.parse(input).getClass().isAssignableFrom(InvalidCommand.class));
    }
}
