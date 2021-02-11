package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the <code>Task</code> class in duke.tasks
 */
public class TestTask {

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        Task task = new Task("CS2103 Quiz");
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        Task task = new Task("CS2103 Quiz");
        assertEquals("CS2103 Quiz", task.getDescription());
    }

    /**
     * Tests that the task's status string is output correctly.
     */
    @Test
    public void testStatusString() {
        Task task = new Task("CS2103 Quiz");
        assertEquals("[ ] CS2103 Quiz", task.getStatusString());
        task.markAsDone();
        assertEquals("[X] CS2103 Quiz", task.getStatusString());
    }
}
