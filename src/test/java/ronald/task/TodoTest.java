package ronald.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ronald.RonaldException;
import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void createTodo_tagOnly_exceptionThrown() {
        assertThrows(RonaldException.class, () -> {
            Todo.createTodo(new String[]{"todo"});
        });
    }

    @Test
    void createTodo_allFields_success() throws RonaldException {
        Task todo = Todo.createTodo(new String[]{"todo", "something something"});
        assertEquals(todo.getFormattedStorageString(), "TODO /&/ 0 /&/ something something\n");
    }
}
