import duke.Deadline;
import duke.Task;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {


    @Test
    public void testToString() {
        LocalDate ld = LocalDate.parse("2021-12-11");
        Task task = new Deadline( "CS2103", ld);
        String actual = task.toString();
        String expected = "[D][✘] CS2103 (by: 2021-12-11)";
        assertEquals(expected, actual);
        assertEquals(expected, actual);
        assertEquals(expected, actual);

    }

}
