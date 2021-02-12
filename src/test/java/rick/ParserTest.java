package rick;

import org.junit.jupiter.api.Test;

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
        } catch (RickException error) {
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
        } catch (RickException error) {
            assertEquals(null, error.getMessage());
        }
    }
}
