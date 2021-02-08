package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Todo t = new Todo("a");
    @Test
    public void testTodoPrinting() {
        assertEquals(t.toString(), "[T][ ] a");
    }
}