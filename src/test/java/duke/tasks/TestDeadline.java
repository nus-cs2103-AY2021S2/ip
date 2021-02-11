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
    private final DateTimeFormatter formatter;

    /**
     * Initializes a <code>Deadline</code> instance with preset properties for testing.
     */
    public TestDeadline() {
        this.formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    }

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        Deadline deadline = new Deadline("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertFalse(deadline.isDone());
        deadline.markAsDone();
        assertTrue(deadline.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        Deadline deadline = new Deadline("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertEquals("CS2103 Quiz", deadline.getDescription());
    }

    /**
     * Tests that the datetime input to the <code>Deadline</code> is correctly converted to a datetime
     * string of the desired format.
     */
    @Test
    public void testDateTimeString() {
        Deadline deadline = new Deadline("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertEquals("2021-02-06 23:30", deadline.getByDateTimeString());
    }

    /**
     * Tests that the task's status string is output correctly.
     */
    @Test
    public void testStatusString() {
        Deadline deadline = new Deadline("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertEquals("[D][ ] CS2103 Quiz (by: 2021-02-06 23:30)", deadline.getStatusString());
        deadline.markAsDone();
        assertEquals("[D][X] CS2103 Quiz (by: 2021-02-06 23:30)", deadline.getStatusString());
    }

    /**
     * Tests that the task is correctly flagged out if overdue.
     */
    @Test
    public void testIsOverdue() {
        Deadline overdueDeadline = new Deadline("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertTrue(overdueDeadline.isOverdue());
        overdueDeadline.markAsDone();
        assertFalse(overdueDeadline.isOverdue());

        Deadline onTimeDeadline = new Deadline("CS2103 Quiz", LocalDateTime.parse("9999-12-31 23:30", this.formatter));
        assertFalse(onTimeDeadline.isOverdue());
    }

    /**
     * Tests that the task is correctly flagged out if urgent.
     */
    @Test
    public void testIsUrgent() {
        Deadline urgentDeadline = new Deadline("CS2103 Quiz", LocalDateTime.now().plusDays(2));
        assertFalse(urgentDeadline.isUrgent(1));

        assertTrue(urgentDeadline.isUrgent(3));
        urgentDeadline.markAsDone();
        assertFalse(urgentDeadline.isUrgent(3));

        Deadline overdueDeadline = new Deadline("CS2103 Quiz", LocalDateTime.parse("2021-02-06 23:30", this.formatter));
        assertFalse(overdueDeadline.isUrgent(1));
    }
}
