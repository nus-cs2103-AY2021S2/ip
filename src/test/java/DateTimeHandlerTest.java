import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateTimeHandlerTest {

    static final String FORMAT = "yyyy-MM-dd HHmm";

    @Test
    public void parseLocalDateTimeIntoString_validLocalDateTimeObject_success() {
        String details = "2021-02-19 2359";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(FORMAT);
        LocalDateTime object = LocalDateTime.parse(details, format);

        String expected = "Feb 19 2021 11:59 PM";
        assertEquals(expected, DateTimeHandler.parseLocalDateTimeIntoString(object));
    }
}
