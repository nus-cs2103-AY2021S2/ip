import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.task.Task;

public class TaskTest {
    private Task task = new Task("return book");

    @Test
    public void dummyTest() {
        assertEquals("[\u2718]return book", task.getTaskInfo());
        assertEquals("[\u2718]", task.getStatusIcon());
        assertEquals("", task.getPeriodDays());
    }
}
