package soonwee.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createToDo_success() {
        ToDo result = new ToDo("Sleep");
        assertEquals("[T][ ] Sleep", result.toString());
    }
}
