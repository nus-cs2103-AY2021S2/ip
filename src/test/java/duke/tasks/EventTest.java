package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

class EventTest {

    @Test
    public void testParseTodo() throws DukeException {
        Task task = Event.parseEvent("This is an event entry /at EventTest");
        assertEquals("[E][ ] This is an event entry (at: EventTest)", task.toString());
        assertEquals(false, task.isComplete());
    }

    @Test
    public void testStorageEntry() throws DukeException {
        Task task = new Event("This is a todo entry", "EventTest");
        assertEquals("E|0|This is a todo entry|EventTest", task.storageEntry());
        assertEquals(false, task.isComplete());
    }

    @Test
    public void testConstructor() throws DukeException {
        Task task = new Event("This is a todo entry", "EventTest", true);
        assertEquals(true, task.isComplete());
    }

    @Test
    public void testParseTodo_exceptionThrown() {
        try {
            Event.parseEvent("This is an event entry EventTest");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of an event must contain a location.", e.getMessage());
        }
    }
}
