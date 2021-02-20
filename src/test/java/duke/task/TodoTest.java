package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testEncode() {
        Todo todo = new Todo("todo");
        assertEquals("T/0/todo", todo.encode());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("todo");
        assertEquals("[T][\u2718] todo", todo.toString());
    }
}
