package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testGetName() {
        Task t = new ToDo("read book");
        assertEquals("read book", t.getName());
    }

    @Test
    public void testGetIcon() {
        Task t = new ToDo("read book");
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void testFinishTask() {
        Task t = new ToDo("read book");
        t.finishTask();
        assertEquals("X", t.getStatusIcon());
    }
}
