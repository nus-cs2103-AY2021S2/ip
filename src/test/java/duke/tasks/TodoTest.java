package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void saveStringTest() {
        Todo todo = new Todo("wash dishes", true);
        assertEquals(todo.saveString(), "T --- 1 --- wash dishes");
    }
}