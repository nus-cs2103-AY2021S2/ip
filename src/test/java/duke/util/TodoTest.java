package duke.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void createTodoTest() {
        assertThrows(DukeException.class, () -> Todo.createTodo(""));
    }
}
