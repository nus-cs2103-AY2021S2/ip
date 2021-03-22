package marvin.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEncode() {
        Event event = new Event("event", "2020-12-12");
        assertEquals(event.encode(), "E/0/event/2020-12-12");
    }

    @Test
    public void testToString() {
        Event event = new Event("event", "2020-12-12");
        assertEquals(event.toString(), "[E][\u2718] event (at: Dec 12 2020)");
    }
}
