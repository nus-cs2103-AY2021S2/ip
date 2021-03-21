import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.task.Todo;

public class TodoTest {
    private Todo todo = new Todo("return book");

    @Test
    public void dummyTest() {
        assertEquals("[T][\u2718]return book", todo.getTaskInfo());
        assertEquals("this task hasn't determine the time", todo.getPeriodDays());
        assertEquals("T | 0 | return book", todo.getTaskInfoOfFile());
        assertEquals("[\u2718]", todo.getStatusIcon());
    }
}
