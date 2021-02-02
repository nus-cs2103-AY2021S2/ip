package jaryl.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        try {
            Todo todo = new Todo("Math Homework");
            assertEquals(todo.toString(), "[T][ ] Math Homework");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
