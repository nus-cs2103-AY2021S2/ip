package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {

    @Test
    public void testFormatToSave() {
        LocalDateTime ldtStart = LocalDateTime.parse("25/12/2021 0000", DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        LocalDateTime ldtEnd = LocalDateTime.parse("25/12/2021 2359", DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        Task task = new Event(ldtStart, ldtEnd, "xmas");
        String actual = task.formatToSave();
        String expected = "E | O | xmas | from: 25-12-2021 0000 | to: 25-12-2021 2359";
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        LocalDateTime ldtStart = LocalDateTime.parse("25/12/2021 0000", DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        LocalDateTime ldtEnd = LocalDateTime.parse("25/12/2021 2359", DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
        Task task = new Event(ldtStart, ldtEnd, "xmas");
        String actual = task.toString();
        String expected = "[E][ ] xmas (from: 25-12-2021 0000 - 25-12-2021 2359)";
        assertEquals(expected, actual);
    }
}
