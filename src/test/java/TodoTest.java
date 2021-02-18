import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void gettingSaveString_normalTodoObject_saveString() {
        assertEquals("T | 0 | finish project", new Todo("finish project").getSaveString());
    }

    @Test
    public void gettingSaveString_todoMarkedAsComplete_saveString() {
        Task todo = new Todo("finish project");
        todo.markAsDone();
        assertEquals("T | 1 | finish project", todo.getSaveString());
    }

    @Test
    public void printingTodoTask_normalTodo_stringReturned() {
        assertEquals("[T][ ] finish project", new Todo("finish project").toString());
    }

    @Test
    public void printingTodoTask_todoMarkedAsDone_stringReturned() {
        Task todo = new Todo("finish project");
        todo.markAsDone();
        assertEquals("[T][X] finish project", todo.toString());
    }

}
