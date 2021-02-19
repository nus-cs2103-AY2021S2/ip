import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void correctDescription() {
        Event event = new Event("task1", LocalDate.parse("2020-02-02"));
        assertEquals("task1", event.getDescription());
    }

    @Test
    public void correctFileStringConversion() {
        Event event = new Event("task1", LocalDate.parse("2020-02-02"));
        assertEquals("E | 0 | task1 | 2020-02-02", event.fileString());
    }

}
