import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TodoTaskTest {
    @Test
    public void Test1(){
        TodoTask task = new TodoTask("todo read books");
        assertEquals("[T][ ] todo read books", task);
    }

    @Test
    public void Test2(){
        TodoTask task = new TodoTask("todo read books");
        task.markDone();
        assertNotEquals("[T][ ] todo read books", task);
    }
}
