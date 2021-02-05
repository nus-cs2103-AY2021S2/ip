import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void getFormattedDescription() throws EmptyArgumentException, BadDateException {
        Deadline deadline = new Deadline("return book/by 2021-02-05");
        assertEquals("Feb 05 2021", deadline.getDateString());
    }
}
