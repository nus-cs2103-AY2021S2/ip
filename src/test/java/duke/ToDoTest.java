package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void initTest() {
        ToDo t = new ToDo("Test");
        assertEquals(t.toString(), "[T][ ] Test");
    }

    @Test
    public void doneTest() {
        ToDo t = new ToDo("Test");
        t.setCompletion(true);
        assertEquals(t.toString(), "[T][X] Test");
    }
}