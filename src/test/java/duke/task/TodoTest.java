package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;

class TodoTest {

    @Test
    void createTodo_tagOnly_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Todo.createTodo(new String[]{"todo"});
        });
    }

    @Test
    void createTodo_allFields_success() throws DukeException {
        Task todo = Todo.createTodo(new String[]{"todo", "something something"});
        assertEquals(todo.getFormattedStorageString(), "TODO /&/ 0 /&/ something something\n");
    }
}
