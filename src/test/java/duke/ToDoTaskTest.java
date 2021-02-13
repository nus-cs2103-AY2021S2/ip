package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDoTaskTest {

    @Test
    public void descriptionTest() {
        ToDoTask newTask = new ToDoTask("read a book");
        assertEquals("read a book", newTask.description);
    }

    @Test
    public void isDoneTest() {
        ToDoTask newTask = new ToDoTask("read a book");
        assertEquals(false, newTask.isDone);
    }

}
