import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import owen.OwenException;
import owen.task.TodoTask;

public class TodoTaskTest {
    @Test
    public void parseTask_correctFormat_success() throws OwenException {
        TodoTask task = new TodoTask("test");
        assertEquals("[T][ ] test", task.toString());
    }

    @Test
    public void parseTask_incorrectFormat_exception() {
        try {
            new TodoTask("");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nThe description of a todo cannot be empty...",
                    exception.getMessage());
        }
    }

    @Test
    public void markAsDone_markAsDone_success() throws OwenException {
        TodoTask task = new TodoTask("test");
        assertEquals("[T][ ] test", task.toString());

        task = task.markAsDone();
        assertEquals("[T][X] test", task.toString());
    }

    @Test
    public void serialize_serialize_success() throws OwenException {
        TodoTask task = new TodoTask("test");
        assertEquals("TODO | false | test", task.serialize());
    }

    @Test
    public void deserialize_correctFormat_success() throws OwenException {
        TodoTask task = TodoTask.deserialize("TODO | false | test");
        assertEquals("[T][ ] test", task.toString());
    }

    @Test
    public void deserialize_incorrectFormat_exception() {
        try {
            TodoTask.deserialize("TODO, false, test");
            fail();
        } catch (Exception exception) {
            assertEquals("Index 1 out of bounds for length 1", exception.getMessage());
        }
    }
}
