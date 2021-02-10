import duke.util.Task;
import duke.util.TaskList;
import duke.util.ToDo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void taskListSizeTest() {
        List<Task> lst = new ArrayList<>();
        TaskList taskList = new TaskList(lst);
        assertEquals(taskList.size(), 0);
    }

    @Test
    public void taskListAddTest() {
        List<Task> lst = new ArrayList<>();
        TaskList taskList = new TaskList(lst);
        taskList.add(new ToDo("task 1"));
        assertEquals(taskList.get(0).getJob(), "task 1");
    }
}