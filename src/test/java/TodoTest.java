import duck.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo todo = new Todo("return book");
    @Test
    public void dummyTest(){
        assertEquals("[T][✘]return book",todo.getTaskInfo());
        assertEquals("this task hasn't determine the time",todo.getPeriodDays());
        assertEquals("T | 0 | return book",todo.getTaskInfoOfFile());
        assertEquals("[✘]",todo.getStatusIcon());
    }
}