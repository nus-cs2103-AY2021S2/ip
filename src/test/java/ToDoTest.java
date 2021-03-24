import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void CompletedToDoOutputTest() {
        assertEquals(
                "[T][X] First Todo",
                new ToDo("First Todo", true).toString()
        );
    }

    @Test
    public void UncompletedToDoOutputTest() {
        assertEquals(
                "[T][ ] First Todo",
                new ToDo("First Todo", false).toString()
        );
    }

    @Test
    public void SetAsDoneTest() {
        assertEquals(
                "[T][X] First Todo",
                new ToDo("First Todo", false).setAsDone().toString()
        );
    }
}