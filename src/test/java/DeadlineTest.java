import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createDeadline_validDateTime_success() {
        assertDoesNotThrow(() -> {
            new Deadline("some item", "01/01/2021 1030");
            new Deadline("some item", "01/01/2021");
        });
    }

    @Test
    public void createDeadline_invalidDateTime_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("some item", "01/01/20");
        });
    }

    @Test
    public void toString_validInputs_outputsCorrectly() {
        String actualOutput = new Deadline("some item", "01/01/2021").toString();
        assertEquals("[D][ ] some item (by: 01 Jan 2021)", actualOutput);
    }
}
