package duke.util;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests decode and encode methods of DateFormatter.
 */
public class DateFormatterTest {
    @Test
    public void testDateDecode() {
        LocalDate date = LocalDate.parse("1996-11-11");
        assertEquals("11 Nov 1996", DateFormatter.decodeDate(date));
    }

    @Test
    public void testDateDecodeForStorage() {
        LocalDate date = LocalDate.parse("1996-11-11");
        assertEquals("1996-11-11", DateFormatter.decodeDateForStorage(date));
    }

    @Test
    public void testDateEncode() throws DukeException {
        LocalDate date = LocalDate.parse("1996-11-11");
        assertEquals(date, DateFormatter.encodeDate("1996-11-11"));
    }
}
