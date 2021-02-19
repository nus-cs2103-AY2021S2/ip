package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getDate_checkDateFormat() {
        assertEquals("|Sunday", new Event("return book", "Sunday").getDate());
    }

    @Test
    public void markAsDone_checkDoneStatus() {
        assertEquals(true, new Event("return book", "Sunday").markAsDone().getDone());
    }
}
