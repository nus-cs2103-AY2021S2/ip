package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test for Todo class.
 */
public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo todo = new Todo("test");
        assertEquals("[T][ ] test", todo.toString());
    }
    @Test
    public void testInfoToStoreConversion() {
        Todo todo = new Todo("test");
        assertEquals("T | 0 | test", todo.infoToStore());
    }
}
