import Duke.Exceptions.DukeException;
import Duke.Exceptions.InvalidDescriptionException;
import Duke.TaskList;
import Duke.Tasks.Priority;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private final TaskList taskListTest = new TaskList();

    @Test
    public void testAddTask() {
        Task t = new ToDo(Priority.HIGH, "run");
        taskListTest.addTask(new ToDo(Priority.LOW, "run"));
        assertEquals(t.toString(), taskListTest.getList().get(0).toString());
    }

    @Test
    public void testDeleteTask() throws InvalidDescriptionException {
        taskListTest.addTask(new ToDo(Priority.HIGH, "run"));
        taskListTest.deleteTask(0);
        assertEquals(0, taskListTest.getList().size());
    }

    @Test
    public void testMarkTask() throws DukeException {
        taskListTest.addTask(new ToDo(Priority.LOW, "run"));
        taskListTest.markTask(0);
        assertEquals("\u2713", taskListTest.getList().get(0).getStatusIcon());
    }
}
