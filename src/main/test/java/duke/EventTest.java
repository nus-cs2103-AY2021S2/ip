package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//
import java.time.LocalDate;

import duke.task.Event;

public class EventTest {

    @Test
    public void initTest() {
        LocalDate date = LocalDate.parse("2021-03-03");
        Event e = new Event("Test", date);
        assertEquals(e.toString(), "[E][ ] Test (at: Mar 3 2021)");
    }

    @Test
    public void doneTest() {
        LocalDate date = LocalDate.parse("2021-03-03");
        Event e = new Event("Test", date);
        e.setCompletion(true);
        assertEquals(e.toString(), "[E][X] Test (at: Mar 3 2021)");
    }
}