import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskTest {

    @Test
    void getStatusIcon() {
        Task task = new Task("read book");
        assertEquals(" ", task.getStatusIcon());
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void testToString() {
        assertEquals("[ ] read book", new Task("read book").toString());
    }
}