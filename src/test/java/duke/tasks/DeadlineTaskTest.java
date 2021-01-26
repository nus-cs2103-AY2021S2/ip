package duke.tasks;

import duke.utils.Formatter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTaskTest {
    private static final String taskName = "Return Book";
    private static final LocalDateTime dateTime = LocalDateTime.parse("24/1/2021 1800", 
            Formatter.INPUT_DATE_FORMATTER);
    
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] Return Book (by: Jan 24 2021, 6:00PM)", 
                new DeadlineTask(taskName, dateTime).toString());
        assertEquals("[D][ ] Return Book (by: Jan 24 2021, 6:00PM)", 
                new DeadlineTask(taskName, false, dateTime).toString());
        assertEquals("[D][X] Return Book (by: Jan 24 2021, 6:00PM)", 
                new DeadlineTask(taskName, true, dateTime).toString());
    }

    @Test
    public void getName_emptyString_success() {
        assertEquals("", new DeadlineTask("", dateTime).getName());
    }

    @Test
    public void getName_nonEmptyString_success() {
        assertEquals(taskName, new DeadlineTask(taskName, dateTime).getName());
    }

    @Test
    public void getTaskType_deadlineTask_success() {
        assertEquals("D", new DeadlineTask("", dateTime).getTaskType());
    }

    @Test
    public void isDone_completedTask_success() {
        assertTrue(new DeadlineTask(taskName, true, dateTime).isDone());
    }

    @Test
    public void isDone_notCompletedTask_success() {
        assertFalse(new DeadlineTask(taskName, false, dateTime).isDone());
    }

    @Test
    public void completeTask_notCompletedTask_success() {
        DeadlineTask deadlineTask = new DeadlineTask(taskName, false, dateTime);
        deadlineTask.completeTask();
        assertTrue(deadlineTask.isDone());
    }
    
    @Test
    public void getDeadline_deadlineTask_success() {
        assertEquals("Jan 24 2021, 6:00PM", new DeadlineTask(taskName, dateTime).getDeadline());
    }
}
