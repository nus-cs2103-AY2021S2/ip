package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testSize() {
        TaskList taskList = new TaskList();
        int actual = taskList.size();
        assertEquals(0, actual);
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("read book");
        taskList.addTask(task1);
        Task task2 = taskList.getTask(0);
        assertEquals(task1, task2);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("read book");
        taskList.addTask(task1);
        Task task2 = taskList.deleteTask(0);
        assertEquals(task1, task2);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testDoneTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("read book");
        taskList.addTask(task1);
        taskList.doneTask(0);
        task1 = taskList.getTask(0);
        String actual = task1.toString();
        String expected = "[T][X] read book";
        assertEquals(expected, actual);
        assertEquals(1, taskList.size());
    }
}
