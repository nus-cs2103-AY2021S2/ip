package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class TestEvent {
    private final String description;
    private final String dateTimeString;
    private final Event event;

    /**
     * Initializes an <code>Event</code> instance with preset properties for testing.
     */
    public TestEvent() {
        this.description = "CS2103 Quiz";
        this.dateTimeString = "2021-02-06 23:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(this.dateTimeString, formatter);
        this.event = new Event(this.description, dateTime);
    }

    /**
     * Test that the <code>Event</code> class processes the event's time correctly.
     */
    @Test
    public void testDateTime() {
        assertEquals(this.dateTimeString, this.event.getAtDateTimeString());
    }

    /**
     * Test that the <code>Event</code> class processes the task's status correctly.
     */
    @Test
    public void testStatus() {
        assertEquals(this.getExpectedStatusString(false), this.event.getStatusString());
        this.event.markAsDone();
        assertEquals(this.getExpectedStatusString(true), this.event.getStatusString());
    }

    /**
     * Builds an expected status string to be compared against the computed one.
     *
     * @param isDone Whether the task is done or not
     * @return The expected status string
     */
    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[E][" + statusSymbol + "] " + this.description + " (at: " + this.dateTimeString + ")";
    }
}
