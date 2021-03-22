package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.model.task.Event;

public class EventTest {
    @Test
    public void getDate_checkDateFormat() {
        assertEquals("|Sunday", new Event("return book", "Sunday").getDate());
    }

    @Test
    public void markAsDone_checkDoneStatus() {
        assertEquals(true, new Event("return book", "Sunday").markAsDone().isDone());
    }
}
