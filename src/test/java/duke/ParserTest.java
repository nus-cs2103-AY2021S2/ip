package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void parseCommandTest_success() throws Exception {
        assertEquals(2, parser.parseCommand("todo"));
    }

    @Test
    public void parseCommandTest_exceptionThrown() {
        try {
            assertEquals(2, parser.parseCommand("gg"));
            fail();
        } catch (DukeException error) {
            assertEquals(null, error.getMessage());
        }
    }

    @Test
    public void parseDoneCommandTest_success() throws Exception {
        assertEquals(2, parser.parseDoneCommand("done 2"));
    }

    @Test
    public void parseDoneCommandTest_exceptionThrown() {
        try {
            assertEquals(2, parser.parseDoneCommand("done"));
            fail();
        } catch (DukeException error) {
            assertEquals(null, error.getMessage());
        }
    }
}
