package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests Todo creation.
 */
public class TodoTest {
    @Test
    public void testTodoCreation() {
        Task task1 = new Todo("Go to the gym");
        assertEquals("[T][ ] Go to the gym", task1.toString());
        Task task2 = new Todo("Complete module tasks", true);
        assertEquals("[T][X] Complete module tasks", task2.toString());
    }
}
