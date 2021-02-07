package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testFormattedText() {
        assertEquals("T | 0 | go for a run",
                new ToDo("go for a run", false).getFormattedData());
    }

    @Test
    public void testToString() {
        assertEquals("[T][] go for a run",
                new ToDo("go for a run", false).toString());
    }
}
