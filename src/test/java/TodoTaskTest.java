import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {
    @Test
    public void newTodoTaskTaskPopulatesFields() {
        TodoTask t = new TodoTask("read book");

        assertEquals(false, t.getCompletionState());
        assertEquals("read book", t.getTaskInfo());
        assertEquals("[T][✗] read book", t.toString());
    }

    public void todoTaskSetDoneChangesCompletionState() {
        TodoTask t = new TodoTask("read book");

        assertEquals(false, t.getCompletionState());
        assertEquals("read book", t.getTaskInfo());
        assertEquals("[T][✗] read book (by: June 6th)", t.toString());

        t.setTaskAsDone();

        assertEquals(true, t.getCompletionState());
        assertEquals("read book", t.getTaskInfo());
        assertEquals("[T][✓] read book (by: June 6th)", t.toString());
    }

    public void todoTaskIsOfTypeTask() {
        TodoTask t = new TodoTask("read book");

        assertTrue(t instanceof Task);
    }
}
