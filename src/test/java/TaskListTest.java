import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void deleteTask_invalidTaskNumber_exceptionThrown() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(new ToDo("homework"));
            TaskList testTaskList = new TaskList(tasks);
            int invalidTaskNumber = tasks.size() + 1;
            testTaskList.deleteTask(invalidTaskNumber);
            fail("OOPS!! The task number is invalid.");
        } catch (DukeException e) {
            String msg = "OOPS!! The task number is invalid.";
            assertEquals(msg, e.getMessage());
        }
    }
}
