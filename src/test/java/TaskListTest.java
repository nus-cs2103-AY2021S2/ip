import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testListLength() {
        TaskList tasks = new TaskList();
        int length = tasks.getListLength();
        assertEquals(length, 0);
    }
}
