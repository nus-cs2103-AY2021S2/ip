package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    private static String taskName = "Project Meeting";
    private static String eventTime = "Friday 2-4pm";

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] Project Meeting (at: Friday 2-4pm)",
                new EventTask(taskName, eventTime).toString());
        assertEquals("[E][ ] Project Meeting (at: Friday 2-4pm)",
                new EventTask(taskName, false, eventTime).toString());
        assertEquals("[E][X] Project Meeting (at: Friday 2-4pm)",
                new EventTask(taskName, true, eventTime).toString());
    }

    @Test
    public void getName_emptyString_success() {
        assertEquals("", new EventTask("", eventTime).getName());
    }

    @Test
    public void getName_nonEmptyString_success() {
        assertEquals("Project Meeting", new EventTask(taskName, eventTime).getName());
    }

    @Test
    public void getTaskType_eventTask_success() {
        assertEquals("E", new EventTask("", eventTime).getTaskType());
    }

    @Test
    public void isDone_completedTask_true() {
        assertTrue(new EventTask(taskName, true, eventTime).isDone());
    }

    @Test
    public void isDone_notCompletedTask_false() {
        assertFalse(new EventTask(taskName, false, eventTime).isDone());
    }

    @Test
    public void completeTask_notCompletedTask_success() {
        EventTask eventTask = new EventTask(taskName, false, eventTime);
        eventTask.completeTask();
        assertTrue(eventTask.isDone());
    }

    @Test
    public void getEventTime_eventTask_success() {
        assertEquals("Friday 2-4pm", new EventTask(taskName, eventTime).getEventTime());
    }
}
