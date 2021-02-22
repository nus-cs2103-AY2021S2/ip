import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private final TaskList list = new TaskList(new Ui());
    private final Task testTask = new Task(" test task", 0);

    @Test
    public void getTaskTest() {
        list.add(testTask);
        assertEquals(testTask, list.get(0));
    }

    @Test
    public void sizeTest() {
        list.add(testTask);
        list.add(testTask);
        assertEquals(2, list.size());
    }
}
