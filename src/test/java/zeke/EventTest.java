package zeke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    private Event event;

    public EventTest() {
        event = new Event("football competition", "2019-10-15");
    }

    @Test
    public void getFormattedDateTest() {
        assertEquals(LocalDate.parse("2019-10-15"), event.getLocalDate());
    }

    @Test
    public void getStatusIconTest() {
        assertEquals(" ", event.getStatusIcon());
    }
}
