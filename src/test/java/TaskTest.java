import duck.task.Deadline;
import duck.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("return book");
    @Test
    public void dummyTest(){
        assertEquals("[✘]return book",task.getTaskInfo());
        assertEquals("[✘]",task.getStatusIcon());
       assertEquals("",task.getPeriodDays());
    }
}
