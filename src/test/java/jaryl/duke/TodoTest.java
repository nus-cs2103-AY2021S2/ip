package jaryl.duke;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

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
