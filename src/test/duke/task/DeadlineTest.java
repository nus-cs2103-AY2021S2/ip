package duke.task;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        assertEquals(new Deadline("return book", LocalDate.parse("2019-10-15")).toString(), "[D][ ] return book (by: Oct 15 2019)");
    }

    @Test
    public void doneDeadlineTest() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2019-10-15"));
        deadline.done();
        assertEquals(deadline.toString(), "[D][X] return book (by: Oct 15 2019)");
    }
}
