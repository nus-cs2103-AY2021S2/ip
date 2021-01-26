package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    public void testPrintTask() {
        assertEquals("[E][ ] Minha's birthday  (at: 2021-02-02 00:00)",
                new Event("Minha's birthday ", LocalDateTime.parse("2021-02-02 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).printTask());
    }
}