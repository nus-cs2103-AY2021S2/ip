package ssagit.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ssagit.taskclass.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() throws Exception {
        task = new Task("test", false);
    }

    @Test
    @DisplayName("Task should be marked as done after markDone() is complete")
    public void testMarkDone() {
        task.markDone();
        assertTrue(task.getIsDone());
    }

    @Test
    @DisplayName("Task should return the correct string")
    public void testToString() {
        assertEquals("todo | not done | test", task.toString());
    }

    @Test
    @DisplayName("Task should return the correct formatted string")
    public void testToFormattedString() {
        assertEquals("[T][ ] test", task.toFormattedString());
    }
}
