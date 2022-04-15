package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void markDoneTest() throws DukeInputException {
        Task t = Deadline.createDeadline("testing /by 2011-01-01");
        assertEquals("[D][ ] testing (by: 1 Jan)", t.toString());
        t = t.markDone();
        assertEquals("[D][X] testing (by: 1 Jan)", t.toString());
    }

    @Test
    public void setHighLowPriorityTest() throws DukeInputException {
        Task t = Deadline.createDeadline("testing /by 2011-01-01");
        assertEquals("[D][ ] testing (by: 1 Jan)", t.toString());

        t = t.setHighPriority();
        assertEquals("[D][ ] IMPT! testing (by: 1 Jan)", t.toString());

        t = t.setLowPriority();
        assertEquals("[D][ ] testing (by: 1 Jan)", t.toString());
    }
}
