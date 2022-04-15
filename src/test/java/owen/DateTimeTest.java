import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import owen.DateTime;
import owen.OwenException;

public class DateTimeTest {
    @Test
    public void parse_correctFormat_success() throws OwenException {
        DateTime dateTime = DateTime.parse("1/1/2021 1400");
        assertEquals("January 1 2021 2:00 PM", dateTime.toString());
    }

    @Test
    public void parse_incorrectFormat_exception() {
        try {
            DateTime.parse("January 1 2021 2:00 PM");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nDate and time must be in DD/MM/YYYY HHMM format...",
                    exception.getMessage());
        }
    }

    @Test
    public void isSoon_correctArgs_success() throws OwenException {
        DateTime dateTime = new DateTime(LocalDateTime.now().plusDays(10));
        assertEquals(false, dateTime.isSoon(9));
        assertEquals(true, dateTime.isSoon(11));
    }
}
