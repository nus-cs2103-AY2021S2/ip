import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ToDoTest {

    @Test
    void testToString() {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
    }
}