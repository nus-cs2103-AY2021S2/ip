package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ToDoFactoryTest {
    @Test
    void createToDo_emptyInput_exceptionThrown() {
        try {
            assertEquals("", new ToDoFactory().createTask("").toString());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Description of todo cannot be empty", e.getMessage());
        }
    }

    @Test
    void createToDo_correctInputs_success() {
        assertEquals("[T][ ][!!]testToDo",
                new ToDoFactory().createTask("testToDo").toString());
        assertEquals("[T][ ][!!]testToDo2",
                new ToDoFactory().createTask("testToDo2").toString());
    }
}
