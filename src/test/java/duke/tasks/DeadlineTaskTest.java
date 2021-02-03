package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    private static final String TASK_NAME = "Return Book";
    private static final LocalDate DEADLINE_DATE = LocalDate.parse("24/1/2021",
            DateTimeFormatter.ofPattern("d/M/y"));
    private static final LocalTime DEADLINE_TIME = LocalTime.parse("1800",
            DateTimeFormatter.ofPattern("HHmm"));

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] Return Book (by: Jan 24 2021, 6:00PM)",
                new DeadlineTask(TASK_NAME, DEADLINE_DATE, DEADLINE_TIME).toString());
        assertEquals("[D][ ] Return Book (by: Jan 24 2021)",
                new DeadlineTask(TASK_NAME, DEADLINE_DATE).toString());
        assertEquals("[D][ ] Return Book (by: Jan 24 2021, 6:00PM)",
                new DeadlineTask(TASK_NAME, false, DEADLINE_DATE, DEADLINE_TIME).toString());
        assertEquals("[D][X] Return Book (by: Jan 24 2021, 6:00PM)",
                new DeadlineTask(TASK_NAME, true, DEADLINE_DATE, DEADLINE_TIME).toString());
    }

    @Test
    public void getName_emptyString_success() {
        assertEquals("", new DeadlineTask("", DEADLINE_DATE, DEADLINE_TIME).getName());
    }

    @Test
    public void getName_nonEmptyString_success() {
        assertEquals(TASK_NAME, new DeadlineTask(TASK_NAME, DEADLINE_DATE, DEADLINE_TIME).getName());
    }

    @Test
    public void getTaskType_deadlineTask_success() {
        assertEquals("D", new DeadlineTask("", DEADLINE_DATE, DEADLINE_TIME).getTaskType());
    }

    @Test
    public void isDone_completedTask_true() {
        assertTrue(new DeadlineTask(TASK_NAME, true, DEADLINE_DATE).isDone());
        assertTrue(new DeadlineTask(TASK_NAME, true, DEADLINE_DATE, DEADLINE_TIME).isDone());
    }

    @Test
    public void isDone_notCompletedTask_false() {
        assertFalse(new DeadlineTask(TASK_NAME, false, DEADLINE_DATE).isDone());
        assertFalse(new DeadlineTask(TASK_NAME, false, DEADLINE_DATE, DEADLINE_TIME).isDone());
    }

    @Test
    public void completeTask_notCompletedTask_success() {
        DeadlineTask deadlineTask = new DeadlineTask(TASK_NAME, false, DEADLINE_DATE, DEADLINE_TIME);
        deadlineTask.completeTask();
        assertTrue(deadlineTask.isDone());
    }

    @Test
    public void getDeadline_deadlineTask_success() {
        assertEquals("Jan 24 2021", new DeadlineTask(TASK_NAME, DEADLINE_DATE).getDeadline());
        assertEquals("Jan 24 2021, 6:00PM", new DeadlineTask(TASK_NAME, DEADLINE_DATE, DEADLINE_TIME).getDeadline());
    }
}
