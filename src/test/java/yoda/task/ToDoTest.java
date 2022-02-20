package yoda.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDoTest {
    @Test
    void testToString() {
        ToDo todo = new ToDo("do that");
        assertEquals(todo.toString(), "[T][ ] do that");
    }
}
