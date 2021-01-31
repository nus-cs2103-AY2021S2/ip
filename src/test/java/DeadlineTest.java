import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void correctDescription() {
        Deadline deadline = new Deadline("task1", LocalDate.parse("2020-02-02"));
        assertEquals("task1", deadline.getDescription());
    }

    @Test
    public void correctFileStringConversion() {
        Deadline deadline = new Deadline("task1", LocalDate.parse("2020-02-02"));
        deadline.markAsDone();
        assertEquals("D | 1 | task1 | 2020-02-02", deadline.fileString());
    }

}
