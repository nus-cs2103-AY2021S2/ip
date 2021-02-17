
import static duke.parser.DatetimeParser.parseDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import duke.exceptions.DukeExceptionIllegalArgument;


public class DatetimeParserTest {


    // No idea how to test for current dates...
    @Test
    void parseDate_datesAfterCurrent_noExceptions() {
        LocalDateTime curr = LocalDateTime.of(2020, 1, 15, 3, 7, 49, 123456789);
        try {
            assertEquals(LocalDateTime.of(2020, 1, 15, 4, 8), parseDate("4:08", curr));
        } catch (DukeExceptionIllegalArgument e) {
            fail("Should not throw exception.");
        }
    }

    @Test
    void parseDate_invalidMinutes_throwException() {
        LocalDateTime now = LocalDateTime.now();
        assertThrowsIllegal(() -> parseDate("3:5", now));
        assertThrowsIllegal(() -> parseDate("3:5 2021-08-09", now));
    }

    @Test
    void parseDate_wrongInput_throwException() {
        assertThrowsIllegal(() -> parseDate("2020")); // year only
        assertThrowsIllegal(() -> parseDate("2020-21")); // no day
        assertThrowsIllegal(() -> parseDate("2020-21-09")); // wrong month
        assertThrowsIllegal(() -> parseDate("09-09-09")); // wrong year, size != 4
        assertThrowsIllegal(() -> parseDate("09-09-2020")); // wrong format, not ISO
        assertThrowsIllegal(() -> parseDate("09-09")); // no year

        assertThrowsIllegal(() -> parseDate("9 march")); // no capitalize
        assertThrowsIllegal(() -> parseDate("9 mar"));
        assertThrowsIllegal(() -> parseDate("mar 9"));
        assertThrowsIllegal(() -> parseDate("march 9"));
        assertThrowsIllegal(() -> parseDate("March")); // no day
        assertThrowsIllegal(() -> parseDate("Mar 2020"));
        assertThrowsIllegal(() -> parseDate("2020 Mar"));
        assertThrowsIllegal(() -> parseDate("2020 Mar 09")); // wrong order
        assertThrowsIllegal(() -> parseDate("09 09 09"));
        assertThrowsIllegal(() -> parseDate("09 Sep 09"));

        assertThrowsIllegal(() -> parseDate("9.3")); // wrong minute
        assertThrowsIllegal(() -> parseDate("9:3"));
        assertThrowsIllegal(() -> parseDate("9:61"));
        assertThrowsIllegal(() -> parseDate("90:3")); // wrong hour
        assertThrowsIllegal(() -> parseDate("30:4"));
        assertThrowsIllegal(() -> parseDate("9.3.2020")); // no such date
        // no such date
    }

    private void assertThrowsIllegal(Executable x) {
        assertThrows(DukeExceptionIllegalArgument.class, x);
    }
}
