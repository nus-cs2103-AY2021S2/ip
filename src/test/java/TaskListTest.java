import timmy.Exceptions.DukeException;
import timmy.Exceptions.InvalidDescriptionException;
import timmy.TaskList;
import timmy.Tasks.Priority;
import timmy.Tasks.Task;
import timmy.Tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private final TaskList taskListTest = new TaskList();

    @Test
    public void testAddTask() {
        Task t = new ToDo(Priority.LOW, "run");
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
        assertEquals("O", taskListTest.getList().get(0).getStatusIcon());
    }
}
