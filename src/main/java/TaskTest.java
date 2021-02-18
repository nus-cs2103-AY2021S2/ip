import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class that conducts JUnit test on the methods from Task class.
 */
class TaskTest {
    /**
     * A test for the getStatusIcon() method in Task class.
     */
    @Test
    void getStatusIcon() {
        Task task = new Task("read book");
        assertEquals(" ", task.getStatusIcon());
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    /**
     * A test for the markAsDone() method in Task class.
     */
    @Test
    void markAsDone() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    /**
     * A test for the toString() method in Task class.
     */
    @Test
    void testToString() {
        assertEquals("[ ] read book", new Task("read book").toString());
    }
}