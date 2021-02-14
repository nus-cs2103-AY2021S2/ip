package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;

public class ToDoTest {

    @Test
    public void dummyTest() {
        ToDo todo = new ToDo("hello");
        assertEquals("ToDo", todo.getTaskType());

    }

}
