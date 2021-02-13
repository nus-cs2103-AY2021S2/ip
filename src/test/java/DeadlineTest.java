import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import command.Deadline;

public class DeadlineTest {
    @Test
    public void testGetBy() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse("12-12-2020 1330");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Deadline test = new Deadline("lunch", date);
        assertEquals(date, test.getBy());
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
        Deadline test = new Deadline("lunch", date);
        assertEquals("[D][ ] lunch (by: Sat Dec 12 13:30:00 SGT 2020)", test.toString());
    }
}
