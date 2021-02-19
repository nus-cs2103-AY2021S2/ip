import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Todo;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testMarKAsDone() {
        assertEquals(true, new Todo("bring water").markAsDone());
    }
}

