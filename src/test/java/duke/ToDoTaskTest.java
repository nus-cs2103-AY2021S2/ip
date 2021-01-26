import duke.ToDoTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void dummyTest(){
        ToDoTask task = new ToDoTask("hello", false);
        assertEquals(task.toString(), "[T] hello");
    }
}
