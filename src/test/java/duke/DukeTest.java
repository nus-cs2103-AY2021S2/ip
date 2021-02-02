package duke;

import duke.tasks.Deadline;
import duke.tasks.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTodoString() {
        assertEquals("[T][\u2717] sleep", new Todo("sleep", false).toString());
    }

    @Test
    public void testDeadlineString() {
        assertEquals("[D][\u2717] sleep (by: Mar 29 2000)", new Deadline("sleep", false, "2000-03-29").toString());
    }

    @Test void testEventString() {
        assertEquals("[T][\u2717] sleep (at: Mar 29 2000)", new Deadline("sleep", false, "2000-03-29").toString());
    }
}