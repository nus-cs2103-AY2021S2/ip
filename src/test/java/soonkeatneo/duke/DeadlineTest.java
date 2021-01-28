package soonkeatneo.duke;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.time.format.DateTimeParseException;
import soonkeatneo.duke.task.Deadline;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createDeadline_validDate_success() {
        assertDoesNotThrow(() -> new Deadline("test", "1999-12-25"));
    }

    @Test
    public void createDeadline_invalidDateFormat_throwsException() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            new Deadline("test", "abcd");
        });
    }

    @Test
    public void toString_validInputs_outputsCorrectly() {
        String expected = new Deadline("some item", "2021-01-01").toString();
        assertEquals("[D][ ] some item (by: Friday, Jan 1 2021)", expected);
    }

}
