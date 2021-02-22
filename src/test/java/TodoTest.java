import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TodoTesting() {
        Todo task = new Todo("eating", false);
        String expected = "[T][ ] eating";
        assertEquals(expected,task.toString());
        String expected2 = "[T][X] eating";
        assertEquals(task.finishTask().toString(), expected2);
        String expected3 = "T|1|eating";
        assertEquals(task.finishTask().saveString(), expected3.toString());
    }
}