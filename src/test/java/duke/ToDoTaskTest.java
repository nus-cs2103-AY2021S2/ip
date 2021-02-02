package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the ToDoTask's constructor, as well as its toString method.
 */
public class ToDoTaskTest {
    @Test
    public void dummyTest() {
        ToDoTask task = new ToDoTask("hello", false);
        assertEquals(task.toString(), "[T] [ ] hello");
    }
}
