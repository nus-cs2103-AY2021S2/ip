package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;

public class TaskListTest {
    @Test
    public void testAddToList() {
        TaskList l = new TaskList();
        Todo t = new Todo("New Todo");
        l.addTask(t);
        assertEquals(t, l.getTask(0));
    }

    @Test
    public void testRemoveFromList() {
        TaskList l = new TaskList();
        Todo t = new Todo("New Todo");
        l.addTask(t);
        assertEquals(t, l.getTask(0));
        l.removeTask(0);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            l.getTask(0);
        });
    }

    @Test
    public void testTaskEventToString() {
        Event t = new Event("Event 1", LocalDateTime.of(2020, 12, 2, 15, 20));
        t.markAsDone();
        String s = TaskList.taskToString(t);
        assertEquals("E|1|Event 1|2020-12-02T15:20:00", s);
    }

    @Test
    public void testParseStringToDeadline() {
        String s = "D|1|Deadline 1|2020-05-08T13:10:00";
        Task t = TaskList.parseTask(s);
        assertTrue(t instanceof Deadline);
        assertTrue(t.getTaskDone());
        assertEquals(LocalDateTime.of(2020, 5, 8, 13, 10, 0), ((Deadline) t).getDeadline());
        assertEquals("Deadline 1", t.getTaskName());
    }
}
