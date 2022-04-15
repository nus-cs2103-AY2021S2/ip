import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testDeadlineStringConversion() {
        LocalDate dateDeadline = LocalDate.parse("2021-01-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Deadline deadline = new Deadline("Buy Birthday Cake", dateDeadline);
        assertEquals("[D][✘]Buy Birthday Cake(by: 20 Jan 2021)", deadline.toString());
    }

    @Test
    public void testGetDeadline() {
        LocalDate dateDeadline = LocalDate.parse("2021-01-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Deadline deadline = new Deadline("Buy Birthday Cake", dateDeadline);
        assertEquals(dateDeadline, deadline.getDeadline());
    }

    @Test
    public void testChangeDeadline() {
        LocalDate dateDeadline = LocalDate.parse("2021-01-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Deadline deadline = new Deadline("Buy Birthday Cake", dateDeadline);
        assertEquals(dateDeadline, deadline.setDeadline(dateDeadline));
    }
}
