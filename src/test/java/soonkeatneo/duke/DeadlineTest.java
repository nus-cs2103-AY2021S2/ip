package soonkeatneo.duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import soonkeatneo.duke.task.Deadline;


public class DeadlineTest {
    @Test
    public void createDeadline_validDate_success() {
        assertDoesNotThrow(() -> new Deadline("test", "1999-12-25"));
    }

    @Test
    public void createDeadline_invalidDateFormat_exceptionThrown() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            new Deadline("test", "abcd");
        });
    }

    @Test
    public void toString_validInputs_correctOutputPrinted() {
        String expected = new Deadline("some item", "2021-01-01").toString();
        assertEquals("[D][ ] some item (by: Friday, Jan 1 2021)", expected);
    }

}
