package duke.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void isDone_newTask_false() {
        Task task = new Task("test");
        assertFalse(task.isDone());
    }

    @Test
    public void setDone_true_true() {
        Task task = new Task("test");
        task.setDone(true);
        assertTrue(task.isDone());
    }

    @Test
    public void getDescriptionTest() {
        Task task = new Task("test");
        assertEquals("test", task.getDescription());
    }

    @Test
    public void getStatusIconTest() {
        Task task = new Task("test");
        assertEquals("\u2718", task.getStatusIcon());
        task.setDone(true);
        assertEquals("\u2713", task.getStatusIcon());
    }
}
