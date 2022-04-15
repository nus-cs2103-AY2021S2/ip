package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void testEventGetType() {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.of(9, 9);
        EventTask task = new EventTask("testing", LocalDateTime.of(date, startTime), LocalDateTime.of(date, endTime));
        assertEquals("[E]", task.getType());
    }
}
