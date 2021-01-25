package lihua.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_normalInitialization_success() {
        ToDo t = new ToDo("I am a todo object.");
        assertEquals("[T][ ] I am a todo object.", t.toString());
    }
}
