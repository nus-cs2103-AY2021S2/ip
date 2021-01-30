package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void markAsDoneTest() {
        Deadline temp = new Deadline(0, "submit assignment",
                LocalDate.parse("2020-10-15"));
        temp.markAsDone();
        assertEquals("[D][X] submit assignment (by: Oct 15 2020)", temp.toString());
    }
}
