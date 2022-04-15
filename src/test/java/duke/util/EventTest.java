package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void markDoneTest() throws DukeInputException {
        Task t = Event.createEvent("testing /at 2011-01-01");
        assertEquals("[E][ ] testing (at: 1 Jan)", t.toString());
        t = t.markDone();
        assertEquals("[E][X] testing (at: 1 Jan)", t.toString());
    }

    @Test
    public void setHighLowPriorityTest() throws DukeInputException {
        Task t = Event.createEvent("testing /at 2011-01-01");
        assertEquals("[E][ ] testing (at: 1 Jan)", t.toString());

        t = t.setHighPriority();
        assertEquals("[E][ ] IMPT! testing (at: 1 Jan)", t.toString());

        t = t.setLowPriority();
        assertEquals("[E][ ] testing (at: 1 Jan)", t.toString());
    }
}
