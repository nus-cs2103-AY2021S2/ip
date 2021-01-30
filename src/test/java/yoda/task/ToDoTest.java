package yoda.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {
    @Test
    void testToString() {
        ToDo todo = new ToDo("do that");
        assertEquals(todo.toString(),
                "[T][ ] do that ---> created on " + todo.formatDateTime());
    }
}