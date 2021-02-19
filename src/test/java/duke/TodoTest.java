package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void getDate_checkDateFormat() {
        assertEquals("", new Todo("return book").getDate());
    }

    @Test
    public void markAsDone_checkDoneStatus() {
        assertEquals(true, new Todo("return book").markAsDone().isDone());
    }
}
