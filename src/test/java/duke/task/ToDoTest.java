package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void validTodo() {
        ToDo dummyToDo = new ToDo("hold diamond hands");
        assertEquals("[TODO]" + " [\u2718] " + "hold diamond hands", dummyToDo.toString());
    }

}
