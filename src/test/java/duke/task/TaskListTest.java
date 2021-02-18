package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TaskListTest {

    @Test
    void doneTask() {
        TaskList.clearAllTasks();
        Todo task = new Todo("Scratch back", " ");
        TaskList.addTask(task);
        TaskList.markDone(1);
        assertEquals("X", task.getDoneStatus());
    }

    @Test
    void deleteTask() {
        TaskList.clearAllTasks();
        Todo task = new Todo("Scratch back", " ");
        TaskList.addTask(task);
        TaskList.deleteTask(1);
        assertEquals(0, TaskList.getTaskList().size());
    }

    @Test
    void listTasks() {
        TaskList.clearAllTasks();
        Todo todo = new Todo("nothing", " ");
        Deadline deadline = new Deadline("Swimming", "2019-10-22", " ");
        TaskList.addTask(todo);
        TaskList.addTask(deadline);

        assertEquals("[T][ ] 1. nothing\n" +
                "[D][ ] 1. Swimming ( 2019-10-22 )\n", TaskList.listTasks());
    }
}