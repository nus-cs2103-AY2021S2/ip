import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private final Task task1 = new Task(" test todo", 0);
    private final Task task2 = new Task(" test deadline with date /by 2020-01-01", 1);
    private final Task task3 = new Task(" test event with time /at 14:25", 2);

    @Test
    public void printTodoTest(){
        assertEquals("[T][ ] test todo", task1.toString());
    }

    @Test
    public void printDeadlineTest() {
        task2.markDone();
        assertEquals("[D][X] test deadline with date (by: Jan 1 2020)", task2.toString());
    }

    @Test
    public void exportEventTest() {
        assertEquals("2 0 test event with time /at 14:25", task3.export());
    }
}
