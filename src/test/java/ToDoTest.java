import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoDescription() {
        ToDo td = new ToDo("run", "T");
        assertEquals(td.description, "run");
        assertEquals(td.isDone, false);
        td.completed();
        assertEquals(td.isDone, true);
    }
}
