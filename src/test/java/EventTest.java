import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import command.Event;

public class EventTest {
    @Test
    public void testGetAt() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse("12-12-2020 1330");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Event test = new Event("lunch", date);
        assertEquals(date, test.getAt());
    }

    @Test
    public void testToString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse("12-12-2020 1330");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Event test = new Event("lunch", date);
        assertEquals("[E][ ] lunch (at: Sat Dec 12 13:30:00 SGT 2020)", test.toString());
    }
}
