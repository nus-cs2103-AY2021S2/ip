package duke.task;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testToStringOutput() {
        Task t1 = new ToDoTask("read book", 1, 1);
        Task t2 = new ToDoTask("return book", 2, 0);
        assertEquals("[T][X] read book", t1.toString());
        assertEquals("[T][] return book", t2.toString());
    }
}