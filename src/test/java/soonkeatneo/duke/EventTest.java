package soonkeatneo.duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import soonkeatneo.duke.task.Event;

public class EventTest {
    @Test
    public void createEvent_validDate_success() {
        assertDoesNotThrow(() -> new Event("test", "1999-12-25"));
    }

    @Test
    public void createEvent_invalidDateFormat_exceptionThrown() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            new Event("test", "abcd");
        });
    }

    @Test
    public void toString_validInputs_correctOutputPrinted() {
        String expected = new Event("some item", "2021-01-01").toString();
        assertEquals("[E][ ] some item (at: Friday, Jan 1 2021)", expected);
    }

}
