package duke;

import Tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void printFormat_todoMarkedasDone() {
        Todo testTodo = new Todo("test", true);
        assertEquals(testTodo.toString(), "[T][X] test");
    }

    @Test
    public void printFormat_todo() {
        Todo testTodo = new Todo("test", false);
        assertEquals(testTodo.toString(), "[T][ ] test");
    }


}

