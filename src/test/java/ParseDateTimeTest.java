import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import datetime.ParseDateTime;
import org.junit.jupiter.api.Test;

public class ParseDateTimeTest {

    @Test
    public void checkUserInputParsing() {
        LocalDateTime d2 = ParseDateTime.parse("30-04 6PM");
        LocalDateTime d3 = ParseDateTime.parse("29-01 5AM");

        assertEquals(
                ParseDateTime.readableString(d2),
                "30 Apr 6PM"
        );

        assertEquals(
                ParseDateTime.readableString(d3),
                "29 Jan 5AM"
        );

        // todo check 12AM, 10PM

    }
}
