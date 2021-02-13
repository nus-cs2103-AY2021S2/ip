package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the <code>Task</code> class in duke.tasks
 */
public class TestTask {
    private final String description;
    private final Task task;

    /**
     * Initializes an instance of <code>ToDo</code> instance for testing.
     */
    public TestTask() {
        this.description = "CS2103 Quiz";
        this.task = new Task(this.description);
    }

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        assertFalse(this.task.isDone());
        this.task.markAsDone();
        assertTrue(this.task.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        assertEquals(this.description, this.task.getDescription());
    }

    /**
     * Tests that the task's status string is output correctly.
     */
    @Test
    public void testStatusString() {
        assertEquals("[ ] " + this.description, this.task.getStatusString());
        this.task.markAsDone();
        assertEquals("[X] " + this.description, this.task.getStatusString());
    }
}
