import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList list = new TaskList();
        Task task = new Todo("eat", "high");

        assertEquals(task, list.addTask(task));
    }
}
