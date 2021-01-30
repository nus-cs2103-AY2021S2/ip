package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][X] return book",
                new Todo(1, "return book").toString());
    }
}
