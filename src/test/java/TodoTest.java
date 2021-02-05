import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("homework");
        assertEquals("[T]" + "[" + todo.getStatusIcon() + "] " + todo.name, todo.toString());
    }

}