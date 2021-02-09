package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TestTask {
    private final String description = "CS2103 Quiz";
    private final Task task = new Task(this.description);

    /**
     * Test that the <code>Task</code> class processes the task's description correctly.
     */
    @Test
    public void testDescription() {
        assertEquals(this.description, this.task.getDescription());
    }

    /**
     * Test that the <code>Task</code> class processes the task's status correctly.
     */
    @Test
    public void testStatus() {
        assertFalse(this.task.isDone());
        assertEquals(this.getExpectedStatusString(false), this.task.getStatusString());

        this.task.markAsDone();
        assertTrue(this.task.isDone());
        assertEquals(this.getExpectedStatusString(true), this.task.getStatusString());
    }

    /**
     * Builds an expected status string to be compared against the computed one.
     *
     * @param isDone Whether the task is done or not
     * @return The expected status string
     */
    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[" + statusSymbol + "] " + this.description;
    }
}
