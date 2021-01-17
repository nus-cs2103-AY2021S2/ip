import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void newDeadlineTaskPopulatesFields() {
        DeadlineTask t = new DeadlineTask("return book", "June 6th");

        assertEquals(false, t.getCompletionState());
        assertEquals("return book", t.getTaskInfo());
        assertEquals("June 6th", t.getDeadline());
        assertEquals("[D][✗] return book (by: June 6th)", t.toString());
    }

    @Test
    public void deadlineTaskSetDoneChangesCompletionState() {
        DeadlineTask t = new DeadlineTask("return book", "June 6th");

        assertEquals(false, t.getCompletionState());
        assertEquals("return book", t.getTaskInfo());
        assertEquals("June 6th", t.getDeadline());
        assertEquals("[D][✗] return book (by: June 6th)", t.toString());

        t.setTaskAsDone();

        assertEquals(true, t.getCompletionState());
        assertEquals("return book", t.getTaskInfo());
        assertEquals("June 6th", t.getDeadline());
        assertEquals("[D][✓] return book (by: June 6th)", t.toString());
    }

    @Test
    public void deadlineTaskIsOfTypeTask() {
        DeadlineTask t = new DeadlineTask("return book", "Today");

        assertTrue(t instanceof Task);
    }
}
