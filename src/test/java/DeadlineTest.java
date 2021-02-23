import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testGetBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2/12/2019",formatter);
        Deadline test = new Deadline("return book", date);
        assertEquals(date, test.getBy());
    }

    @Test
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2/12/2019",formatter);
        Deadline test = new Deadline("return book", date);
        assertEquals("[D][Incompleted] lunch (by:Dec 12 2019)", test.toString());
    }
}