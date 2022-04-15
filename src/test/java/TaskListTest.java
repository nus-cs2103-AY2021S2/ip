import duchess.TaskList;
import duchess.Tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testStoreTask() {
        TaskList tasklist = new TaskList();
        Task task = new Task("Buy candies");
        tasklist.storeTask(task);
        assertEquals(tasklist.getTaskList().contains(task), true);
    }

    @Test
    public void testDeleteTask() {
        TaskList tasklist = new TaskList();
        Task task = new Task("Buy candies");
        tasklist.storeTask(task);
        tasklist.deleteTask(1);
        assertEquals(tasklist.getTaskList().contains(task), false);
    }

    @Test
    public void testGetTask() {
        TaskList tasklist = new TaskList();
        Task task = new Task("Buy candies");
        tasklist.storeTask(task);
        assertEquals(tasklist.getTask(1), task);
    }

    @Test
    public void testMarkComplete() {
        Task task = new Task("Buy candies");
        TaskList tasklist = new TaskList();
        tasklist.storeTask(task);
        tasklist.markComplete(1);
        assertEquals(tasklist.getTaskList().get(0).getCompleted(), true);
    }

}
