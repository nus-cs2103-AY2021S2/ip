package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDateTest(){
        assertEquals("|Sunday", new Deadline("return book", "Sunday").getDate());
    }

    @Test
    public void parseDateTest(){
        assertEquals("Oct 15 2019", new Deadline("return book", "Sunday").parseDate("2019-10-15").format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
