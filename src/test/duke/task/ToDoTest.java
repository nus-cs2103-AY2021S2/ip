package duke.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void addTask() {
    }

    @Test
    void testToString() {
        ToDo todo = new ToDo("play badminton");
        assertEquals("[T][ ] play badminton", todo.toString());
    }
}