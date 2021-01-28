package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTaskTest {
    private static final String taskName = "Return Book";
    private static final LocalDate date = LocalDate.parse("24/1/2021",
            DateTimeFormatter.ofPattern("d/M/y"));
    private static final LocalTime time = LocalTime.parse("1800",
            DateTimeFormatter.ofPattern("HHmm"));

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] Return Book (by: Jan 24 2021, 6:00PM)",
                new DeadlineTask(taskName, date, time).toString());
        assertEquals("[D][ ] Return Book (by: Jan 24 2021)",
                new DeadlineTask(taskName, date).toString());
        assertEquals("[D][ ] Return Book (by: Jan 24 2021, 6:00PM)",
                new DeadlineTask(taskName, false, date, time).toString());
        assertEquals("[D][X] Return Book (by: Jan 24 2021, 6:00PM)",
                new DeadlineTask(taskName, true, date, time).toString());
    }

    @Test
    public void getName_emptyString_success() {
        assertEquals("", new DeadlineTask("", date, time).getName());
    }

    @Test
    public void getName_nonEmptyString_success() {
        assertEquals(taskName, new DeadlineTask(taskName, date, time).getName());
    }

    @Test
    public void getTaskType_deadlineTask_success() {
        assertEquals("D", new DeadlineTask("", date, time).getTaskType());
    }

    @Test
    public void isDone_completedTask_true() {
        assertTrue(new DeadlineTask(taskName, true, date).isDone());
        assertTrue(new DeadlineTask(taskName, true, date, time).isDone());
    }

    @Test
    public void isDone_notCompletedTask_false() {
        assertFalse(new DeadlineTask(taskName, false, date).isDone());
        assertFalse(new DeadlineTask(taskName, false, date, time).isDone());
    }

    @Test
    public void completeTask_notCompletedTask_success() {
        DeadlineTask deadlineTask = new DeadlineTask(taskName, false, date, time);
        deadlineTask.completeTask();
        assertTrue(deadlineTask.isDone());
    }

    @Test
    public void getDeadline_deadlineTask_success() {
        assertEquals("Jan 24 2021", new DeadlineTask(taskName, date).getDeadline());
        assertEquals("Jan 24 2021, 6:00PM", new DeadlineTask(taskName, date, time).getDeadline());
    }
}
