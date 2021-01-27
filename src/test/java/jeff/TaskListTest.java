package jeff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;



public class TaskListTest {
    @Test
    public void getNumTasksTest() {
        TaskList tl = new TaskList();
        tl.addTask(new ToDo("blah"));
        assertEquals(1, tl.getNumTasks());
    }
}
