package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testFormatToSave() {
        Task task = new Todo("read book");
        String actual = task.formatToSave();
        String expected = "T | O | read book";
        assertEquals(actual, expected);
    }

    @Test
    public void testToString() {
        Task task = new Todo("read book");
        String actual = task.toString();
        String expected = "[T][ ] read book";
        assertEquals(actual, expected);
    }
}