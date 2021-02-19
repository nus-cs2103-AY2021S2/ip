package rick;

import org.junit.jupiter.api.Test;
import rick.exceptions.RickException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void parseCommandTest_success() throws Exception {
        assertEquals(Command.TODO, parser.parseCommand("todo"));
    }

    @Test
    public void parseCommandTest_exceptionThrown() {
        try {
            assertEquals(2, parser.parseCommand("gg"));
            fail();
        } catch (RickException error) {
            assertEquals("Rick's sorry, but Rick don't know what that means.\n" +
                    "Type \"help\" to view the list of available commands.", error.getMessage());
        }
    }

    @Test
    public void parseDoneCommandTest_success() throws Exception {
        assertEquals(1, parser.parseDoneCommand("done 2"));
    }

    @Test
    public void parseDoneCommandTest_exceptionThrown() {
        try {
            assertEquals(2, parser.parseDoneCommand("done"));
            fail();
        } catch (RickException error) {
            assertEquals("Invalid done command format!\n" +
                    "Valid format: done <task index>", error.getMessage());
        }
    }
}
