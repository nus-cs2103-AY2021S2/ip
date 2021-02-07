package duke.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void createTodoTest() throws DukeInputException {
        assertThrows(DukeException.class, () -> Todo.createTodo(""));
        assertDoesNotThrow(() -> Todo.createTodo("a"));
    }

    @Test
    public void markDoneTest() throws DukeInputException {
        Task t = Todo.createTodo("testing");
        assertEquals("[T][ ] testing", t.toString());
        t = t.markDone();
        assertEquals("[T][X] testing", t.toString());
    }
}
