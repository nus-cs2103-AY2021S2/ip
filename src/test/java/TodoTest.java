import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testSaveTask() {
        assertEquals("T | 1 | join sports club",
                new Todo("join sports club", true).saveTask());
        assertEquals("T | 0 | join sports club",
                new Todo("join sports club", false).saveTask());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][\u2713] join sports club",
                new Todo("join sports club", true).toString());
        assertEquals("[T][ ] join sports club",
                new Todo("join sports club", false).toString());
    }
}
