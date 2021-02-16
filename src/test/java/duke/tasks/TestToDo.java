package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the <code>ToDo</code> class in duke.tasks
 */
public class TestToDo {
    private final String description;
    private final ToDo toDo;

    /**
     * Initializes an instance of <code>ToDo</code> instance for testing.
     */
    public TestToDo() {
        this.description = "CS2103 Quiz";
        this.toDo = new ToDo(this.description);
    }

    /**
     * Tests that the task's can be marked as done correctly.
     */
    @Test
    public void testIsDone() {
        assertFalse(this.toDo.isDone());
        this.toDo.markAsDone();
        assertTrue(this.toDo.isDone());
    }

    /**
     * Tests that the task's description is processed correctly.
     */
    @Test
    public void testDescription() {
        assertEquals(this.description, this.toDo.getDescription());
    }
}
