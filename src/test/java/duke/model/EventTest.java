package duke.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createNewEvent() {
        assertEquals("[T][X] read book",
                (new ToDo(true, "read book")).toString());
    }
}
