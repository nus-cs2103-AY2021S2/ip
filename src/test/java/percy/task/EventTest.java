package percy.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void testEventInit() {
        Event event = new Event("Pet expo", LocalDate.now(), LocalTime.now());
        String expectedToString = "[E][ ] Pet expo (at: "
                + LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
        assertEquals(expectedToString, event.toString());
    }
}
