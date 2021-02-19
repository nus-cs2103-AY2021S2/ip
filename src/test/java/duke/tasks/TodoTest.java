package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

class TodoTest {

    @Test
    public void testParseTodo() throws DukeException {
        Task task = Todo.parseTodo("This is a todo entry");
        assertEquals("[T][ ] This is a todo entry", task.toString());
        assertEquals(false, task.isComplete());
    }

    @Test
    public void testStorageEntry() throws DukeException {
        Task task = new Todo("This is a todo entry");
        assertEquals("T|0|This is a todo entry", task.storageEntry());
        assertEquals(false, task.isComplete());
    }

    @Test
    public void testConstructor() throws DukeException {
        Task task = new Todo("This is a todo entry", true);
        assertEquals(true, task.isComplete());
    }

    @Test
    public void testParseTodo_exceptionThrown() {
        try {
            Todo.parseTodo("");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
