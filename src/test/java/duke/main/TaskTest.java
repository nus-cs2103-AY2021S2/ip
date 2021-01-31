package duke.main;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test for Task class.
 */
public class TaskTest {
    @Test
    public void testStringConversion() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void testInfoToStoreConversion() {
        Task task = new Task("test");
        assertEquals("0 | test", task.infoToStore());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
        task.markAsDone();
        assertEquals("[X] test", task.toString());
    }
}
