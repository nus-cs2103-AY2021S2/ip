package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    @Test
    void markAsDone_success() {
        Event event = new Event("Homework", LocalDate.parse("2020-01-01"));
        assertTrue(event.markAsDone());
    }

    @Test
    void markAsDone_alreadyMarked_fail() {
        Event event = new Event("Homework", LocalDate.parse("2020-01-01"));
        assertTrue(event.markAsDone());
        assertFalse(event.markAsDone());
    }
}
