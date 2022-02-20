package yoda.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void checkDateTimeFormatter() {
        Deadline deadline = new Deadline("finish this", "2021-10-10 1300");
        assertEquals("Oct 10 2021 1300", deadline.formatDateTime());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("Test task");
        todo.markAsDone();
        assertTrue(todo.isDone);
    }

    @Test
    public void testMarkToBeDeleted() {
        ToDo todo = new ToDo("Test task");
        todo.markToBeDeleted();
        assertTrue(todo.isMarkedToBeDeleted);
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("complete that", "2021-01-01 1900");
        assertEquals("[D][ ] complete that ---> by Jan 1 2021 1900", deadline.toString());
    }
}
