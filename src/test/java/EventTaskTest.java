import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    public void newEventTaskPopulatesFields() {
        EventTask t = new EventTask("group meeting", "June 6th");

        assertEquals(false, t.getCompletionState());
        assertEquals("group meeting", t.getTaskInfo());
        assertEquals("June 6th", t.getTime());
        assertEquals("[E][✗] group meeting (at: June 6th)", t.toString());
    }

    @Test
    public void eventTaskSetDoneChangesCompletionState() {
        EventTask t = new EventTask("group meeting", "June 6th");

        assertEquals(false, t.getCompletionState());
        assertEquals("group meeting", t.getTaskInfo());
        assertEquals("June 6th", t.getTime());
        assertEquals("[E][✗] group meeting (at: June 6th)", t.toString());

        t.setTaskAsDone();

        assertEquals(true, t.getCompletionState());
        assertEquals("group meeting", t.getTaskInfo());
        assertEquals("June 6th", t.getTime());
        assertEquals("[E][✓] group meeting (at: June 6th)", t.toString());
    }

    @Test
    public void eventTaskIsOfTypeTask() {
        EventTask t = new EventTask("group meeting", "Today");

        assertTrue(t instanceof Task);
    }
}
