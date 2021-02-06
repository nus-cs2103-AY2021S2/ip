package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
