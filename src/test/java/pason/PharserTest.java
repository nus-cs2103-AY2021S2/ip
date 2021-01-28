package pason;

import pason.exceptions.PasonException;
import pason.parser.Parser;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PharserTest {
    @Test
    public void validateDeadline_missingDate_exceptionThrown() throws Exception {
        try {
            assertEquals("Please enter a by date for 'Complete lab'", Parser.validateDeadline("deadline Complete lab"));
            fail(); // the test should not reach this line
        } catch (PasonException e) {
            assertEquals("Please enter a by date for 'Complete lab'", e.getMessage());
        }
    }

    @Test
    public void validateDeadline_validFormat_success() throws Exception {
        assertNotNull(Parser.validateDeadline("deadline Complete lab /by 29/01/2021 1200"));
    }
}
