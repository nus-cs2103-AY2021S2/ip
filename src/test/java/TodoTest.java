import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {

    @Test
    public void testMarKAsDone() {
        assertEquals(true, new Todo("bring water").markAsDone());
    }
}

