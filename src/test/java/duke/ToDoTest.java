package duke;

import org.junit.jupiter.api.Test;
import duke.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {

    @Test
    public void dummyTest() {
        ToDo todo = new ToDo("hello");
        assertEquals("ToDo",todo.getTaskType());

    }

}
