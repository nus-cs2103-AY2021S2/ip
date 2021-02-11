package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the <code>Event</code> class in duke.tasks
 */
public class TestEvent {
    private final DateTimeFormatter formatter;

    /**
     * Initializes a <code>Event</code> instance with preset properties for testing.
     */
    public TestEvent() {
        this.formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    }

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        Event event = new Event("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertFalse(event.isDone());
        event.markAsDone();
        assertTrue(event.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        Event event = new Event("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertEquals("CS2103 Quiz", event.getDescription());
    }

    /**
     * Tests that the datetime input to the <code>Event</code> is correctly converted to a datetime
     * string of the desired format.
     */
    @Test
    public void testDateTimeString() {
        Event event = new Event("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertEquals("2021-02-06 23:30", event.getAtDateTimeString());
    }

    /**
     * Tests that the task's status string is output correctly.
     */
    @Test
    public void testStatusString() {
        Event event = new Event("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertEquals("[E][ ] CS2103 Quiz (at: 2021-02-06 23:30)", event.getStatusString());
        event.markAsDone();
        assertEquals("[E][X] CS2103 Quiz (at: 2021-02-06 23:30)", event.getStatusString());
    }

    /**
     * Tests that the task is correctly flagged out if overdue.
     */
    @Test
    public void testIsOverdue() {
        Event overdueEvent = new Event("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertTrue(overdueEvent.isOverdue());
        overdueEvent.markAsDone();
        assertFalse(overdueEvent.isOverdue());

        Event onTimeEvent = new Event("CS2103 Quiz", LocalDateTime.parse("9999-12-31 23:30", this.formatter));
        assertFalse(onTimeEvent.isOverdue());
    }

    /**
     * Tests that the task is correctly flagged out if urgent.
     */
    @Test
    public void testIsUrgent() {
        Event urgentEvent = new Event("CS2103 Quiz", LocalDateTime.now().plusDays(2));
        assertFalse(urgentEvent.isUrgent(1));

        assertTrue(urgentEvent.isUrgent(3));
        urgentEvent.markAsDone();
        assertFalse(urgentEvent.isUrgent(3));

        Event overdueEvent = new Event("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertFalse(overdueEvent.isUrgent(1));
    }
}
