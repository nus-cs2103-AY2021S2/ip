import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void newTaskPopulatesFields() {
        Task t = new Task("testing task");

        assertEquals(false, t.getCompletionState());
        assertEquals("testing task", t.getTaskInfo());
        assertEquals("[✗] testing task", t.toString());
    }

    @Test
    public void taskSetDoneChangesTaskCompletionState() {
        Task t = new Task("testing task");
        t.setTaskAsDone();

        assertEquals(true, t.getCompletionState());
        assertEquals("testing task", t.getTaskInfo());
        assertEquals("[✓] testing task", t.toString());
    }
}
