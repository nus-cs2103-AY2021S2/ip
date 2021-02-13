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
    private final LocalDateTime dateTime;
    private final String dateTimeString;
    private final String description;
    private final Event event;

    /**
     * Initializes an instance of <code>Event</code> instance for testing.
     */
    public TestEvent() {
        this.dateTime = LocalDateTime.now();
        this.dateTimeString = this.dateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
        this.description = "CS2103 Quiz";
        this.event = new Event(this.description, this.dateTime);
    }

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        assertFalse(this.event.isDone());
        this.event.markAsDone();
        assertTrue(this.event.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        assertEquals(this.description, this.event.getDescription());
    }

    /**
     * Tests that the event's datetime is processed correctly.
     */
    @Test
    public void testDateTime() {
        assertEquals(this.dateTime, this.event.getAtDateTime());
    }

    /**
     * Tests that the datetime input to the <code>Event</code> is correctly converted to a datetime
     * string of the desired format.
     */
    @Test
    public void testDateTimeString() {
        assertEquals(this.dateTimeString, this.event.getAtDateTimeString());
    }

    /**
     * Tests that the task's status string is output correctly.
     */
    @Test
    public void testStatusString() {
        assertEquals(
                "[E][ ] " + this.description + " (at: " + this.dateTimeString + ")",
                this.event.getStatusString()
        );

        this.event.markAsDone();
        assertEquals(
                "[E][X] " + this.description + " (at: " + this.dateTimeString + ")",
                this.event.getStatusString()
        );
    }

    /**
     * Tests that the task is correctly flagged out if overdue.
     */
    @Test
    public void testIsOverdue() {
        Event overdueEvent = new Event(this.description, this.dateTime.minusDays(1));
        assertTrue(overdueEvent.isOverdue());

        // Done tasks should not be overdue
        overdueEvent.markAsDone();
        assertFalse(overdueEvent.isOverdue());

        Event onTimeEvent = new Event(this.description, this.dateTime.plusDays(1));
        assertFalse(onTimeEvent.isOverdue());
    }

    /**
     * Tests that the task is correctly flagged out if urgent.
     */
    @Test
    public void testIsUrgent() {
        Event urgentEvent = new Event(this.description, this.dateTime.plusDays(2));
        assertFalse(urgentEvent.isUrgent(1));

        // Done tasks should not be urgent
        assertTrue(urgentEvent.isUrgent(3));
        urgentEvent.markAsDone();
        assertFalse(urgentEvent.isUrgent(3));

        // Overdue tasks should not be urgent
        Event overdueEvent = new Event(this.description, this.dateTime.minusDays(1));
        assertFalse(overdueEvent.isUrgent(1));
    }
}
