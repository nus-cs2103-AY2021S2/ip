package quackers.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    void markAsDone_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-01-01 00:00", DeadlineTest.DATETIME_FORMATTER);
        Deadline deadline = new Deadline("Homework", dateTime);
        deadline.markAsDone();
        String statusIcon = deadline.getStatusIcon();
        assertEquals("*", statusIcon);
    }

    @Test
    void markAsDone_fail() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-01-01 00:00", DeadlineTest.DATETIME_FORMATTER);
        Deadline deadline = new Deadline("Homework", dateTime);
        deadline.markAsDone();
        deadline.markAsUndone();
        String statusIcon = deadline.getStatusIcon();
        assertNotEquals("*", statusIcon);
    }

    @Test
    void markAsUndone_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-01-01 00:00", DeadlineTest.DATETIME_FORMATTER);
        Deadline deadline = new Deadline("Homework", dateTime);
        deadline.markAsDone();
        deadline.markAsUndone();
        String statusIcon = deadline.getStatusIcon();
        assertEquals(" ", statusIcon);
    }

    @Test
    void markAsUndone_fail() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-01-01 00:00", DeadlineTest.DATETIME_FORMATTER);
        Deadline deadline = new Deadline("Homework", dateTime);
        deadline.markAsDone();
        String statusIcon = deadline.getStatusIcon();
        assertNotEquals(" ", statusIcon);
    }
}
