package antonio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import antonio.task.Task;
import antonio.task.ToDoTask;

public class TaskListTest {
    @Test
    public void testAddToTaskList() {
        Task dummyTask = new ToDoTask("dummy task", 1);
        TaskList taskList = new TaskList();
        taskList.addTask(dummyTask);
        assertEquals("\n1. [T][] dummy task", taskList.toString());
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testDeleteTaskFromList() {
        Task dummyTask = new ToDoTask("dummy task", 1);
        TaskList taskList = new TaskList();
        taskList.addTask(dummyTask);
        taskList.removeTask(1);
        assertEquals(0, taskList.getSize());
    }
}
