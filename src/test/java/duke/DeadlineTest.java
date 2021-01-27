package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void toStringFormat_dateIncluded_success() throws Exception {
        Deadline d = new Deadline("Read book", "2020-03-04 2359");
        assertEquals( "[D][ ] Read book (by: Mar 4 2020 2359)",d.toString());
    }
    @Test
    public void savedStringFormat_checkFormat_success() throws Exception {
        Deadline d = new Deadline("Do Homework", "2021-03-04");
        assertEquals("D | 0 | Do Homework | 2021-03-04", d.getSavedStringFormat());
    }
    @Test
    public void toStringFormat_checkFormat_throwsException() {
        try {
            Deadline d = new Deadline("do Something", "this date");
            fail(); // test should not reach this line.
        } catch (Exception e) {
            assertEquals("Sorry Unable to Parse date for Deadline. "
                    + "Did you try to do it in yyyy-mm-dd format?", e.getMessage());
        }
    }


}
