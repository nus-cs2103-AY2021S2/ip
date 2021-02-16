import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void isSameDayTest() {
        Task testTask = new Task("homework", "2021-2-16 10:00", false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        LocalDate testDate1 = LocalDate.parse("2021-2-16", formatter);
        assertTrue(testTask.isSameDay(testDate1));

        LocalDate testDate2 = LocalDate.parse("2020-2-16", formatter);
        assertFalse(testTask.isSameDay(testDate2));
    }

    @Test
    public void markAsDoneTest() {
        Task testTask = new Task("homework", false);
        assertFalse(testTask.isDone());

        testTask.markAsDone();
        assertTrue(testTask.isDone());
    }
}
