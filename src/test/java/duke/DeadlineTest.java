package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Used to test Deadline, a subclass of Task.
 */
public class DeadlineTest {
    @Test
    public void testName() throws DukeException {
        Deadline task = new Deadline("return book (by: 2020-05-01)");
        assertEquals("[D][ ] return book (by: May 01 2020)", task.toString());
    }

    @Test
    public void testMarkAsDone() throws DukeException {
        Deadline task = new Deadline("return book (by: 2020-05-01)");
        task.markAsDone();
        assertEquals("[D][X] return book (by: May 01 2020)", task.toString());
    }
}
