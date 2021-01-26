import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskListTest = new TaskList();

    @Test
    public void testAddTask() {
        Task t = new ToDo("run");
        taskListTest.addTask(new ToDo("run"));
        assertEquals(t.toString(), taskListTest.getList().get(0).toString());
    }

    @Test
    public void testDeleteTask() throws InvalidDescriptionException {
        Task t = new ToDo("run");
        taskListTest.addTask(new ToDo("run"));
        taskListTest.deleteTask(0);
        assertEquals(0, taskListTest.getList().size());
    }

    @Test
    public void testMarkTask() throws DukeException {
        Task t = new ToDo("run");
        taskListTest.addTask(new ToDo("run"));
        taskListTest.markTask(0);
        assertEquals("\u2713", taskListTest.getList().get(0).getStatusIcon());
    }
}
