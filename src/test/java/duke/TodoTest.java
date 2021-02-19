package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getDate_checkDateFormat() {
        assertEquals("", new Todo("return book").getDate());
    }

    @Test
    public void markAsDone_checkDoneStatus() {
        assertEquals(true, new Todo("return book").markAsDone().getDone());
    }
}
