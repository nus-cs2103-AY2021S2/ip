package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Tasks.Events;


public class EventTest {
    @Test
    public void printFormat_eventMarkedasDone() {
        Events testEvent = new Events("test", true, "Monday", "10:00", "12:00");
        assertEquals(testEvent.toString(), "[E][X] test (at: Monday 10:00 - 12:00)");
    }

    @Test
    public void printFormat_event() {
        Events testEvent = new Events("test", false, "Monday", "10:00", "12:00");
        assertEquals(testEvent.toString(), "[E][ ] test (at: Monday 10:00 - 12:00)");
    }
}
