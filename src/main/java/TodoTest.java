import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TodoTest {
    
    @Test
    public void createTodoTest() {
        assertThrows(DukeException.class, () -> Todo.createTodo(""));
    }
}
