import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddItem() {
        TaskList taskList = new TaskList();
        taskList.addTask("todo homework");
        assertEquals(1, taskList.fetchTasks().size());
    }
}
