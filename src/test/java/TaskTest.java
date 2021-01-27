import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testGetStatusIcon() {
        assertEquals("\u2713", new Todo("join sports club", true).getStatusIcon());
        assertEquals(" ", new Todo("join sports club", false).getStatusIcon());
    }

    @Test
    public void testSaveTask() {
        assertEquals("1 | join sports club", new Task("join sports club", true).saveTask());
        assertEquals("0 | join sports club", new Task("join sports club", false).saveTask());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[\u2713] join sports club",
                new Task("join sports club", true).toString());
        assertEquals("[ ] join sports club",
                new Task("join sports club", false).toString());
    }
}
