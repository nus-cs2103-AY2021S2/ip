import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventStringConversion(){
        Event event = new Event("Attend seminar", "Sunday");
        assertEquals("[E][✘]Attend seminar(at: Sunday)", event.toString());
    }

    @Test
    public void testGetTimeSlot(){
        Event event = new Event("Attend seminar", "Sunday");
        assertEquals("Sunday", event.getTimeslot());
    }

    @Test public void testChangeTimeSlot(){
        Event event = new Event("Attend seminar", "Sunday");
        assertEquals("Monday", event.changeTimeslot("Monday"));
    }
}