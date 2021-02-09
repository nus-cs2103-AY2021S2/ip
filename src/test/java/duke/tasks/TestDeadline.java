package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class TestDeadline {
    private final String description;
    private final String dateTimeString;
    private final Deadline deadline;

    /**
     * Initializes a <code>Deadline</code> instance with preset properties for testing.
     */
    public TestDeadline() {
        this.description = "CS2103 Quiz";
        this.dateTimeString = "2021-02-06 23:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(this.dateTimeString, formatter);
        this.deadline = new Deadline(this.description, dateTime);
    }

    /**
     * Test that the <code>Deadline</code> class processes the event's time correctly.
     */
    @Test
    public void testDateTime() {
        assertEquals(this.dateTimeString, this.deadline.getByDateTimeString());
    }

    /**
     * Test that the <code>Deadline</code> class processes the task's status correctly.
     */
    @Test
    public void testStatus() {
        assertEquals(this.getExpectedStatusString(false), this.deadline.getStatusString());
        this.deadline.markAsDone();
        assertEquals(this.getExpectedStatusString(true), this.deadline.getStatusString());
    }

    /**
     * Builds an expected status string to be compared against the computed one.
     *
     * @param isDone Whether the task is done or not
     * @return The expected status string
     */
    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[D][" + statusSymbol + "] " + this.description + " (by: " + this.dateTimeString + ")";
    }
}
