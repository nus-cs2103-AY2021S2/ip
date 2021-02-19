package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void getDate_checkDateFormat() {
        assertEquals("|Sunday", new Deadline("return book", "Sunday").getDate());
    }

    @Test
    public void parseDate_unparsableDate_nullReturned() {
        assertEquals(null, new Deadline("return book", "Sunday").parseDate("Sunday"));
    }

    @Test
    public void markAsDone_checkDoneStatus() {
        assertEquals(true, new Deadline("return book", "Sunday").markAsDone().isDone());
    }
}
