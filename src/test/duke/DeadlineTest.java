package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    public void testPrintTask() {
        assertEquals("[D][ ] Minha's birthday  (by: 2021-02-02 00:00)",
                new Deadline("Minha's birthday ", LocalDateTime.parse("2021-02-02 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).printTask());
    }
}