package blarb;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void stringify_correctInputFormat_success() {
        assertEquals("[D][\u2718] sth (by: Sep 27 2000 AD)",
                new Deadline("sth", "2000-09-27").toString());
        assertEquals("D / 0 / sth / 2000-09-27",
                new Deadline("sth", "2000-09-27").encode());
    }

    @Test
    public void stringify_wrongInputFormat_exceptionThrown() {
        try {
            assertEquals("[D][\u2718] sth (by Sep 27 2000 AD) ",
                    new Deadline("sth", "2000/09/27").toString());
            fail();
        } catch (DateTimeParseException ex) {
            assertEquals("Text '2000/09/27' could not be parsed at index 4", ex.getMessage());
        }
    }
}
