package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void isDone_newTask_notDone() {
        Task task = new Task("task");
        assertFalse(task.isDone());
    }

    @Test
    void markAsDone() {
        Task task = new Task("task");
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    void getStatusIcon_notDoneTask() {
        Task task = new Task("task");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    void getStatusIcon_doneTask() {
        Task task = new Task("task");
        task.markAsDone();
        assertEquals("\u2718", task.getStatusIcon());
    }
}
