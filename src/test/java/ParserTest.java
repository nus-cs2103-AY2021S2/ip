import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

public class ParserTest {
    @Test
    public void getDateTime_correctInput_success() throws Exception {

        assertEquals("Monday", new Parser(new TaskList())
                .getDateTime("read book /by Monday", " /by "));

        assertEquals("23 Jan 2021", new Parser(new TaskList())
                .getDateTime("read book /by 23 Jan 2021", " /by "));
    }

    @Test
    public void getDateTime_incorrectInput_exceptionThrown() {
        try {
            assertEquals("Monday", new Parser(new TaskList())
                    .getDateTime("read book /at Monday", " /by "));
        } catch (DukeException e) {
            assertEquals("Statement does not contain  /by ", e.getMessage());
        }
    }
}
