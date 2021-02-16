import duke.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toFileString_void_success() {
            ToDo todo = new ToDo("junit test");
        assertEquals(todo.toFileString(),
                "TODO[ ] junit test");
    }
}
