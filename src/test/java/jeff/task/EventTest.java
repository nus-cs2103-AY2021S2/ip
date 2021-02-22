package jeff.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getSymbolTest() {
        Event event = new Event("book club meeting", "2020-02-05", "13:00");
        assertEquals("E", event.getSymbol());
    }

    @Test
    public void getDateTest() {
        Event event = new Event("book club meeting", "2020-02-05", "13:00");
        assertEquals(LocalDate.parse("2020-02-05"), event.getDate());
    }

    @Test
    public void getTimeTest() {
        Event event = new Event("book club meeting", "2020-02-05", "13:00");
        assertEquals(LocalTime.parse("13:00"), event.getTime());
    }

    @Test
    public void toStringTest1() {
        Event event = new Event("book club meeting", "2020-02-05", "13:30");
        assertEquals("[E][  ] book club meeting (at: 5 FEBRUARY 2020 13:30)", event.toString());
    }

    @Test
    public void toStringTest2() {
        Event event = new Event("book club meeting", "2020-02-05", "13:30");
        event.setDone();
        assertEquals("[E][X] book club meeting (at: 5 FEBRUARY 2020 13:30)", event.toString());
    }
}
