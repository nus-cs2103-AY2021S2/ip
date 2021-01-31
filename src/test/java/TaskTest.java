import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("todo read book", "1/2/2021", "1400", "[T]", true);

    @Test
    public void testDateFormatter() {
        assertEquals("1 Feb 2021", task.dateFormatter(task.getDate()));
    }

    @Test
    public void testTimeFormatter() {
        assertEquals("2:00pm", task.timeFormatter(task.getTime()));
    }
}