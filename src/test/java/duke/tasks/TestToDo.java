package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the <code>ToDo</code> class in duke.tasks
 */
public class TestToDo {

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        ToDo toDo = new ToDo("CS2103 Quiz");
        assertFalse(toDo.isDone());
        toDo.markAsDone();
        assertTrue(toDo.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        ToDo toDo = new ToDo("CS2103 Quiz");
        assertEquals("CS2103 Quiz", toDo.getDescription());
    }

    /**
     * Tests that the task's status string is output correctly.
     */
    @Test
    public void testStatusString() {
        ToDo toDo = new ToDo("CS2103 Quiz");
        assertEquals("[T][ ] CS2103 Quiz", toDo.getStatusString());
        toDo.markAsDone();
        assertEquals("[T][X] CS2103 Quiz", toDo.getStatusString());
    }
}
