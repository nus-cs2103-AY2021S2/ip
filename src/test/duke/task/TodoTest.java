package duke.task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void todoTest() {
        assertEquals(new ToDo("borrow book").toString(), "[T][ ] borrow book");
    }

    @Test
    public void doneTodoTest() {
        ToDo todo = new ToDo("borrow book");
        todo.done();
        assertEquals(todo.toString(), "[T][X] borrow book");
    }
}
