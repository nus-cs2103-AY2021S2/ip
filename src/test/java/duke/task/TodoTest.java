package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void setDone() {
        Todo todo = new Todo("random", false);
        assertEquals(true, todo.setDone().isDone);
    }

    @Test
    void encode() {
        Todo todo = new Todo("random", false);
        String expected = "T" + "|" + "PENDING" + "|" + todo.msg;
        assertEquals(expected, todo.encode());
    }

    @Test
    void testToString() {
        Todo todo = new Todo("random", false);
        String expected = "[T]" + "[ ] " + todo.msg;
        assertEquals(expected, todo.toString());
    }
}