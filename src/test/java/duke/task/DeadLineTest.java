package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLineTest {
    @Test
    public void getDateTest() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-04-16 1800", df);
        Deadline deadline = new Deadline("clean room", dateTime);
        assertEquals("Apr 16 2021 18:00", deadline.getTaskDate());
    }
}
