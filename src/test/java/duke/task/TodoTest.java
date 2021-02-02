package duke.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    void markAsDone_success() {
        Todo todo = new Todo("Homework");
        assertTrue(todo.markAsDone());
    }

    @Test
    void markAsDone_alreadyMarked_fail() {
        Todo todo = new Todo("Homework");
        todo.markAsDone();
        assertFalse(todo.markAsDone());
    }
}
