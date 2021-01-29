import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void Test1(){
        TodoTask task = new TodoTask("todo read books");
        assertEquals(task, task);
    }

    @Test
    public void Test2(){
        TodoTask task = new TodoTask("todo read books");
        task.markDone();
        assertEquals(task, task);
    }
}
