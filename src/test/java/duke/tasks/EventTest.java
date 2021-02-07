package duke.tasks;

import duke.tasks.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    @DisplayName("Testing getDateTime method in Event class")
    public void testGetDateTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        LocalDate testDate = LocalDate.parse("2021-02-05");
        LocalTime testTime = LocalTime.parse("17:00");

        String result = testDate.format(dateFormatter)
                        + " "
                        + testTime.format(timeFormatter);

        assertEquals(new Event("Some event", "NUS 2021-02-05 17:00").getDateTime(), result);
    }
}