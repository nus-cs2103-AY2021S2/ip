import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void newTaskPopulatesFields() {
        Task t = new Task("testing task");

        assertFalse(t.getCompletionState());
        assertEquals("testing task", t.getTaskInfo());
        assertEquals("[X] testing task", t.toString());
    }

    @Test
    public void taskSetDoneChangesTaskCompletionState() {
        Task t = new Task("testing task");
        t.setTaskAsDone();

        assertTrue(t.getCompletionState());
    }

    @Test
    public void taskDefaultCompletionStateIsFalse() {
        Task t = new Task("testing task");

        assertFalse(t.getCompletionState());
    }

    @Test
    public void taskSetArchivedSetsArchivedState() {
        Task t = new Task("testing task");
        t.setArchived(true);

        assertTrue(t.isArchived());
    }

    @Test
    public void taskDefaultArchivedStateIsFalse() {
        Task t = new Task("testing task");

        assertFalse(t.isArchived());
    }
}
