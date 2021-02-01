package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TaskTest {

    @Test
    void done() {
        TaskList.clearAllTask();
        Todo task = new Todo("Scratch back", " ");
        TaskList.addTask(task);
        TaskList.markDone(1);
        assertEquals("X", task.getDoneStatus());
    }

    @Test
    void delete() {
        TaskList.clearAllTask();
        Todo task = new Todo("Scratch back", " ");
        TaskList.addTask(task);
        TaskList.deleteTask(1);
        assertEquals(0, TaskList.getTaskList().size());
    }
}