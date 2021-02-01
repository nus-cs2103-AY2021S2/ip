package duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createNewEvent() {
        assertEquals("[T][X] read book", (new ToDo(true, "read book")).toString());
    }
}
