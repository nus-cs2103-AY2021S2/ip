import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    public void newEventTaskPopulatesFields() {
        EventTask t = new EventTask("group meeting", "02/03/2020 1400");

        assertEquals(false, t.getCompletionState());
        assertEquals("group meeting", t.getTaskInfo());
        assertEquals("Mar 02 2020 1400", t.getDate());
        assertEquals("[E][X] group meeting (at: Mar 02 2020 1400)", t.toString());
    }

    @Test
    public void eventTaskSetDoneChangesCompletionState() {
        EventTask t = new EventTask("group meeting", "02/03/2020 1400");

        assertEquals(false, t.getCompletionState());
        assertEquals("group meeting", t.getTaskInfo());
        assertEquals("Mar 02 2020 1400", t.getDate());
        assertEquals("[E][X] group meeting (at: Mar 02 2020 1400)", t.toString());

        t.setTaskAsDone();

        assertEquals(true, t.getCompletionState());
        assertEquals("group meeting", t.getTaskInfo());
        assertEquals("Mar 02 2020 1400", t.getDate());
        assertEquals("[E][O] group meeting (at: Mar 02 2020 1400)", t.toString());
    }

    @Test
    public void eventTaskIsOfTypeTask() {
        EventTask t = new EventTask("group meeting", "02/03/2020 1400");

        assertTrue(t instanceof Task);
    }
}
