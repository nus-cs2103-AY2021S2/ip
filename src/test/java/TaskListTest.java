import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testListLength() {
        TaskList tasks = new TaskList();
        assertEquals(tasks.listLength, 0);
    }
}
