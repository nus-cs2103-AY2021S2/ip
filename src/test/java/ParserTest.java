import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommand_invalidDeadlineTime_exceptionThrown() {
        Parser parser = new Parser();
        try {
            String[] invalidInputs = {"deadline homework /by 2021/1/1 12:00",
                                      "deadline homework /by 2021-1-1",
                                      "deadline homework /by 1-1-2021"};

            for (String testInput : invalidInputs) {
                parser.parseCommand(testInput);
                fail("OOPS!! Please follow the correct date/time format: yyyy-MM-dd HH:mm");
            }
        } catch (DukeException e) {
            String expectedMessage = "OOPS!! Please follow the correct date/time format: yyyy-MM-dd HH:mm";
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
