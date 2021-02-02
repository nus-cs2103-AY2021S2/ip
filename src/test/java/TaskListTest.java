import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList taskList = new TaskList();
    Todo todo = new Todo("read book");
    Deadline deadline = new Deadline("deadline return book", "7/2/2021", "1700");
    Event event = new Event("event team meeting", "8/2/2021", "1400-1600");

    @Test
    public void numOfTask() {
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        assertEquals(3, taskList.numOfTasks());
    }
}
