package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void markDoneTest() throws DukeInputException {
        Task t = Todo.createTodo("testing");
        assertEquals("[T][ ] testing", t.toString());
        t = t.markDone();
        assertEquals("[T][X] testing", t.toString());
    }

    @Test
    public void setHighLowPriorityTest() throws DukeInputException {
        Task t = Todo.createTodo("testing");
        assertEquals("[T][ ] testing", t.toString());
        t = t.setHighPriority();
        assertEquals("[T][ ] IMPT! testing", t.toString());
        t = t.setLowPriority();
        assertEquals("[T][ ] testing", t.toString());
    }
}
