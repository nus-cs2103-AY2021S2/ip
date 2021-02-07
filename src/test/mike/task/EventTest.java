package mike.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    public void eventConstructorTest() {
        Event event = new Event("project meeting", "2021-01-25");
        assertEquals(event.toString(), "[E][\u2718]project meeting (at: Jan 25 2021)");
    }

    @Test
    public void eventMarkTest() {
        Event event = new Event("project meeting", "2021-01-25");
        event.markAsDone();
        assertEquals(event.toString(), "[E][\u2713]project meeting (at: Jan 25 2021)");
    }
}