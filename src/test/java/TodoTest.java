import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {
        Todo task = new Todo("read book");
        String actual = task.toString();
        assertEquals("T | 0 | read book", actual);
    }

}
