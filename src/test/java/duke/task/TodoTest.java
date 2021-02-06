package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("todo");
        assertEquals("[T][\u2718] todo", todo.toString());
    }

}
