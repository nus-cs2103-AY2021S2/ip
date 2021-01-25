package duke;

import org.junit.Test;
import duke.task.Task;
import duke.task.ToDoTask;

import static junit.framework.TestCase.assertEquals;

public class TaskListTest {
    @Test
    public void testAddToTaskList() {
        Task dummyTask = new ToDoTask("dummy task", 1);
        TaskList taskList = new TaskList();
        taskList.addTask(dummyTask);
        assertEquals("Here are the tasks in your list:\n" +
                "1. [T][] dummy task\n", taskList.toString());
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
