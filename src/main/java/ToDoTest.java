import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class that conducts JUnit test on the methods from ToDo class.
 */
class ToDoTest {
    /**
     * A test for the toString() method in ToDo class.
     */
    @Test
    void testToString() {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
    }
}