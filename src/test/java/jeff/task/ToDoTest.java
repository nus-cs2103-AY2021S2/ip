package jeff.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getSymbolTest() {
        ToDo todo = new ToDo("read book");
        assertEquals("T", todo.getSymbol());
    }

    @Test
    public void toStringTest1() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][  ] read book", todo.toString());
    }

    @Test
    public void toStringTest2() {
        ToDo todo = new ToDo("read book");
        todo.setDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
