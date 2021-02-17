import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void newDeadlineTaskPopulatesFields() {
        DeadlineTask t = new DeadlineTask("return book", "02/03/2020 1400");

        assertEquals(false, t.getCompletionState());
        assertEquals("return book", t.getTaskInfo());
        assertEquals("Mar 02 2020 1400", t.getDeadline());
        assertEquals("[D][X] return book (by: Mar 02 2020 1400)", t.toString());
    }

    @Test
    public void deadlineTaskSetDoneChangesCompletionState() {
        DeadlineTask t = new DeadlineTask("return book", "02/03/2020 1400");

        assertEquals(false, t.getCompletionState());
        assertEquals("return book", t.getTaskInfo());
        assertEquals("Mar 02 2020 1400", t.getDeadline());
        assertEquals("[D][X] return book (by: Mar 02 2020 1400)", t.toString());

        t.setTaskAsDone();

        assertEquals(true, t.getCompletionState());
        assertEquals("return book", t.getTaskInfo());
        assertEquals("Mar 02 2020 1400", t.getDeadline());
        assertEquals("[D][O] return book (by: Mar 02 2020 1400)", t.toString());
    }

    @Test
    public void deadlineTaskIsOfTypeTask() {
        DeadlineTask t = new DeadlineTask("return book", "02/03/2020 1400");

        assertTrue(t instanceof Task);
    }
}
