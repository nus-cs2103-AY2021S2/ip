package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void getStatusIcon_DoneTask() {
        Task task = new Task("task");
        task.markAsDone();
        assertEquals("\u2718", task.getStatusIcon());
    }
}