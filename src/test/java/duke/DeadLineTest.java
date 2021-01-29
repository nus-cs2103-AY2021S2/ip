package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLineTest {

    @Test
    public void testFormatToSave() {
        LocalDateTime ldt = LocalDateTime.parse("25/12/2021 1800", DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        Task task = new Deadline(ldt, "cook turkey");
        String actual = task.formatToSave();
        String expected = "D | O | cook turkey | by: 25-12-2021 1800";
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        LocalDateTime ldt = LocalDateTime.parse("25/12/2021 1800", DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        Task task = new Deadline(ldt, "cook turkey");
        String actual = task.toString();
        String expected = "[D][ ] cook turkey (by: 25-12-2021 1800)";
        assertEquals(expected, actual);
    }
}
