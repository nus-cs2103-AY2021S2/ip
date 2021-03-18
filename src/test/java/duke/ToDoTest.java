package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for to-do class.
 */
public class ToDoTest {

    @Test
    public void testGetName() {
        ToDo t = new ToDo("read book");
        assertEquals("read book", t.getName());
    }

    @Test
    public void testGetIcon() {
        ToDo t = new ToDo("read book");
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void testFinishTask() {
        ToDo t = new ToDo("read book");
        t.finishTask();
        assertEquals("X", t.getStatusIcon());
    }
}
