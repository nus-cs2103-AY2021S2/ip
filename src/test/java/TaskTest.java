import duchess.Tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testStringConversion() {
        assertEquals("[ ] Buy candies", new Task("Buy candies").toString());
    }

    @Test
    public void testGetName() {
        assertEquals("Make acai", new Task("Make acai").getName());
    }

    @Test
    public void testGetCompleted() {
        assertEquals(false, new Task("Make acai").getCompleted());
    }

    @Test
    public void testCheckTask() {
        Task task = new Task("Buy Candies");
        task.checkTask();
        assertEquals(true, task.getCompleted());
    }

    @Test
    public void testGetStatusIcon() {
        Task task = new Task("Buy Candies");
        task.checkTask();
        assertEquals("X", task.getStatusIcon());
    }

}
