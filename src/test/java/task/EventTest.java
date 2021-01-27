package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getSimpleAtTest() {
        DateTimeFormatter format = DateTimeFormatter
                .ofPattern("dd/MM/yyyy, hh:mma", Locale.US);
        LocalDateTime dateTime = LocalDateTime.parse("25/10/1997, 10:30PM", format);
        Event event = new Event("test", dateTime);
        assertEquals(event.getSimpleAt(), dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mma")));

    }

    @Test
    public void getAtTest() {
        DateTimeFormatter format = DateTimeFormatter
                .ofPattern("dd/MM/yyyy, hh:mma", Locale.US);
        LocalDateTime dateTime = LocalDateTime.parse("25/10/1997, 10:30PM", format);
        Event event = new Event("test", dateTime);
        assertEquals(event.getAt(), "25 Oct 1997, 10:30PM");
    }
}
