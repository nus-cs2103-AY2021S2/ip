import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEventStringConversion() {
        Event event = new Event("Attend seminar", "Sunday");
        assertEquals("[E][✘]Attend seminar(at: Sunday)", event.toString());
    }

    @Test
    public void testGetEventTime() {
        Event event = new Event("Attend seminar", "Sunday");
        assertEquals("Sunday", event.getEventTime());
    }

    @Test public void testSetEventTime() {
        Event event = new Event("Attend seminar", "Sunday");
        assertEquals("Monday", event.setEventTime("Monday"));
    }
}
