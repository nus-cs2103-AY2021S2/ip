package quackers.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    void markAsDone_success() {
        Todo todo = new Todo("Homework");
        todo.markAsDone();
        String statusIcon = todo.getStatusIcon();
        assertEquals("*", statusIcon);
    }

    @Test
    void markAsDone_fail() {
        Todo todo = new Todo("Homework");
        todo.markAsDone();
        todo.markAsUndone();
        String statusIcon = todo.getStatusIcon();
        assertNotEquals("*", statusIcon);
    }

    @Test
    void markAsUndone_success() {
        Todo todo = new Todo("Homework");
        todo.markAsDone();
        todo.markAsUndone();
        String statusIcon = todo.getStatusIcon();
        assertEquals(" ", statusIcon);
    }

    @Test
    void markAsUndone_fail() {
        Todo todo = new Todo("Homework");
        todo.markAsDone();
        String statusIcon = todo.getStatusIcon();
        assertNotEquals(" ", statusIcon);
    }
}
