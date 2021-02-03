import main.java.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("todo read book", "1/2/2021", "1400", "[T]", true);

    @Test
    public void testFormatDate() {
        task.formatDate();
        assertEquals("1 Feb 2021", task.getDate());
    }

    @Test
    public void testFormatTime() {
        task.formatTime();
        assertEquals("2:00pm", task.getTime());
    }
}