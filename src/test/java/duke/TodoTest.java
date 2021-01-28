package duke;

import Tasks.Todos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void printFormat_todoMarkedasDone() {
        Todos testTodo = new Todos("test", true);
        assertEquals(testTodo.toString(), "[T][X] test");
    }

    @Test
    public void printFormat_todo() {
        Todos testTodo = new Todos("test", false);
        assertEquals(testTodo.toString(), "[T][ ] test");
    }

    
}

