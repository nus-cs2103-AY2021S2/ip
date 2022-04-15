import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import chadbot.task.Deadline;
import chadbot.task.Event;
import chadbot.task.Task;
import chadbot.task.ToDo;

public class TaskTest {
    @Test
    public void setTaskAsDone_beforeSetDone() {
        assertFalse(new Task("").isDone());
    }

    @Test
    public void setToDoAsDone_beforeSetDone() {
        assertFalse(new ToDo("").isDone());
    }

    @Test
    public void setDeadlineAsDone_beforeSetDone() {
        assertFalse(new Deadline("", LocalDate.parse("1111-11-11")).isDone());
    }

    @Test
    public void setEventAsDone_beforeSetDone() {
        assertFalse(new Event("", LocalDate.parse("1111-11-11")).isDone());
    }

    @Test
    public void setTaskAsDone_afterSetDone() {
        Task task = new Task("");
        task.setDone();
        assertTrue(task.isDone());
    }

    @Test
    public void setToDoAsDone_afterSetDone() {
        ToDo todo = new ToDo("");
        todo.setDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void setDeadlineAsDone_afterSetDone() {
        Deadline deadline = new Deadline("", LocalDate.parse("1111-11-11"));
        deadline.setDone();
        assertTrue(deadline.isDone());
    }

    @Test
    public void setEventAsDone_afterSetDone() {
        Event event = new Event("", LocalDate.parse("1111-11-11"));
        event.setDone();
        assertTrue(event.isDone());
    }

    @Test
    public void getTaskName() {
        assertEquals("", new Task("").getName());
        assertEquals("hi", new Task("hi").getName());
        assertEquals("qwertyuiopasdfghjklzxcvbnm",
                new Task("qwertyuiopasdfghjklzxcvbnm").getName());
    }

    @Test
    public void getTodoName() {
        assertEquals("", new ToDo("").getName());
        assertEquals("hi", new ToDo("hi").getName());
        assertEquals("qwertyuiopasdfghjklzxcvbnm",
                new ToDo("qwertyuiopasdfghjklzxcvbnm").getName());
    }

    @Test
    public void getDeadlineName() {
        assertEquals("", new Deadline("", LocalDate.parse("1111-11-11")).getName());
        assertEquals("hi", new Deadline("hi", LocalDate.parse("1111-11-11")).getName());
        assertEquals("qwertyuiopasdfghjklzxcvbnm",
                new Deadline("qwertyuiopasdfghjklzxcvbnm", LocalDate.parse("1111-11-11")).getName());
    }

    @Test
    public void getEventName() {
        assertEquals("", new Event("", LocalDate.parse("1111-11-11")).getName());
        assertEquals("hi", new Event("hi", LocalDate.parse("1111-11-11")).getName());
        assertEquals("qwertyuiopasdfghjklzxcvbnm",
                new Event("qwertyuiopasdfghjklzxcvbnm", LocalDate.parse("1111-11-11")).getName());
    }

    @Test
    public void getTaskToString() {
        Task t = new Task("say hi");
        assertEquals("[ ][ ] say hi", t.toString());
        t.setDone();
        assertEquals("[ ][X] say hi", t.toString());
    }

    @Test
    public void getToDoToString() {
        ToDo t = new ToDo("say hi");
        assertEquals("[T][ ] say hi", t.toString());
        t.setDone();
        assertEquals("[T][X] say hi", t.toString());
    }

    @Test
    public void getDeadlineToString() {
        Deadline d = new Deadline("say hi", LocalDate.parse("1111-11-11"));
        assertEquals("[D][ ] say hi (by: Nov 11 1111)", d.toString());
        d.setDone();
        assertEquals("[D][X] say hi (by: Nov 11 1111)", d.toString());
    }

    @Test
    public void getEventToString() {
        Event e = new Event("say hi", LocalDate.parse("1111-11-11"));
        assertEquals("[E][ ] say hi (at: Nov 11 1111)", e.toString());
        e.setDone();
        assertEquals("[E][X] say hi (at: Nov 11 1111)", e.toString());
    }

    @Test
    public void getDeadlineDate() {
        assertEquals("1111-11-11",
                new Deadline("", LocalDate.parse("1111-11-11")).getDate());
    }

    @Test
    public void getEventDate() {
        assertEquals("1111-11-11",
                new Event("", LocalDate.parse("1111-11-11")).getDate());
    }

    @Test
    public void getDeadlineDateAsLocalDate() {
        assertEquals(LocalDate.parse("1111-11-11"),
                new Deadline("", LocalDate.parse("1111-11-11")).getDateAsLocalDate());
    }

    @Test
    public void getEventDateAsLocalDate() {
        assertEquals(LocalDate.parse("1111-11-11"),
                new Event("", LocalDate.parse("1111-11-11")).getDateAsLocalDate());
    }
}
