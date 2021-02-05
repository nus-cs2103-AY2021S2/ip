package popo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    private static final String TASK_NAME = "Project Meeting";
    private static final String EVENT_TIME = "Friday 2-4pm";

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] Project Meeting (at: Friday 2-4pm)",
                new EventTask(TASK_NAME, EVENT_TIME).toString());
        assertEquals("[E][ ] Project Meeting (at: Friday 2-4pm)",
                new EventTask(TASK_NAME, false, EVENT_TIME).toString());
        assertEquals("[E][X] Project Meeting (at: Friday 2-4pm)",
                new EventTask(TASK_NAME, true, EVENT_TIME).toString());
    }

    @Test
    public void getName_emptyString_success() {
        assertEquals("", new EventTask("", EVENT_TIME).getName());
    }

    @Test
    public void getName_nonEmptyString_success() {
        assertEquals("Project Meeting", new EventTask(TASK_NAME, EVENT_TIME).getName());
    }

    @Test
    public void getTaskType_eventTask_success() {
        assertEquals("E", new EventTask("", EVENT_TIME).getTaskType());
    }

    @Test
    public void isDone_completedTask_true() {
        assertTrue(new EventTask(TASK_NAME, true, EVENT_TIME).isDone());
    }

    @Test
    public void isDone_notCompletedTask_false() {
        assertFalse(new EventTask(TASK_NAME, false, EVENT_TIME).isDone());
    }

    @Test
    public void completeTask_notCompletedTask_success() {
        EventTask eventTask = new EventTask(TASK_NAME, false, EVENT_TIME);
        eventTask.completeTask();
        assertTrue(eventTask.isDone());
    }

    @Test
    public void getEventTime_eventTask_success() {
        assertEquals("Friday 2-4pm", new EventTask(TASK_NAME, EVENT_TIME).getEventTime());
    }
}
