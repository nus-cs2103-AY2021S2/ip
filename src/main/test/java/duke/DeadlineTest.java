package duke;

import duke.task.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void initTest() {
        LocalDate date = LocalDate.parse("2021-03-03");
        Deadline d = new Deadline("Test", date);
        assertEquals(d.toString(), "[D][ ] Test (by: Mar 3 2021)");
    }

    @Test
    public void doneTest() {
        LocalDate date = LocalDate.parse("2021-03-03");
        Deadline d = new Deadline("Test", date);
        d.setCompletion(true);
        assertEquals(d.toString(), "[D][X] Test (by: Mar 3 2021)");
    }
}