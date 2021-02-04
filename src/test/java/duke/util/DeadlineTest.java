package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void createDeadlineTest() throws DukeInputException {
        assertThrows(DukeException.class, () -> Deadline.createDeadline(""));
        assertThrows(DukeException.class, () -> Deadline.createDeadline("a"));
        assertThrows(DukeException.class, () -> Deadline.createDeadline("a /by"));
        assertThrows(DukeException.class, () -> Deadline.createDeadline("a /by 1"));
        assertThrows(DukeException.class, () -> Deadline.createDeadline("a 2011-01-01"));
        assertThrows(DukeException.class, () -> Deadline.createDeadline("a/by2011-01-01"));

        Task t = Deadline.createDeadline("testing /by 2011-01-01");
        assertEquals("[D][ ] testing (by: 1 Jan)", t.toString());
        t = t.markDone();
        assertEquals("[D][X] testing (by: 1 Jan)", t.toString());
    }
}
