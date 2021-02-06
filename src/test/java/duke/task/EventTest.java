package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getDateTest() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-03-20 1800", df);
        Deadline deadline = new Deadline("birthday party", dateTime);
        assertEquals("Mar 20 2021 18:00", deadline.getTaskDate());
    }
}
