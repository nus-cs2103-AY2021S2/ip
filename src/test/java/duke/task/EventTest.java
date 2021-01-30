package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getFileStringTest() {
        assertEquals("E // 0 // meeting // 2020-12-15",
                new Event(0, "meeting", LocalDate.parse("2020-12-15")).getFileString());
    }
}
