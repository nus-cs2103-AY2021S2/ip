import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
}
