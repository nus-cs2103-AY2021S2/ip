package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the <code>Deadline</code> class in duke.tasks
 */
public class TestDeadline {
    private final LocalDateTime dateTime;
    private final String dateTimeString;
    private final String description;
    private final Deadline deadline;

    /**
     * Initializes an instance of <code>Deadline</code> instance for testing.
     */
    public TestDeadline() {
        this.dateTime = LocalDateTime.now();
        this.dateTimeString = this.dateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
        this.description = "CS2103 Quiz";
        this.deadline = new Deadline(this.description, this.dateTime);
    }

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        assertFalse(this.deadline.isDone());
        this.deadline.markAsDone();
        assertTrue(this.deadline.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        assertEquals(this.description, this.deadline.getDescription());
    }

    /**
     * Tests that the deadline's datetime is processed correctly.
     */
    @Test
    public void testDateTime() {
        assertEquals(this.dateTime, this.deadline.getByDateTime());
    }

    /**
     * Tests that the datetime input to the <code>Deadline</code> is correctly converted to a datetime
     * string of the desired format.
     */
    @Test
    public void testDateTimeString() {
        assertEquals(this.dateTimeString, this.deadline.getByDateTimeString());
    }

    /**
     * Tests that the task is correctly flagged out if overdue.
     */
    @Test
    public void testIsOverdue() {
        Deadline overdueDeadline = new Deadline(this.description, this.dateTime.minusDays(1));
        assertTrue(overdueDeadline.isOverdue());

        // Done tasks should not be overdue
        overdueDeadline.markAsDone();
        assertFalse(overdueDeadline.isOverdue());

        Deadline onTimeDeadline = new Deadline(this.description, this.dateTime.plusDays(1));
        assertFalse(onTimeDeadline.isOverdue());
    }

    /**
     * Tests that the task is correctly flagged out if urgent.
     */
    @Test
    public void testIsUrgent() {
        Deadline urgentDeadline = new Deadline(this.description, this.dateTime.plusDays(2));
        assertFalse(urgentDeadline.isUrgent(1));

        // Done tasks should not be urgent
        assertTrue(urgentDeadline.isUrgent(3));
        urgentDeadline.markAsDone();
        assertFalse(urgentDeadline.isUrgent(3));

        // Overdue tasks should not be urgent
        Deadline overdueDeadline = new Deadline(this.description, this.dateTime.minusDays(1));
        assertFalse(overdueDeadline.isUrgent(1));
    }
}
