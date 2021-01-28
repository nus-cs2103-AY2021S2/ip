package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    @Test
    void markAsDone_success() {
        Deadline deadline = new Deadline("Homework", LocalDate.parse("2020-01-01"));
        assertTrue(deadline.markAsDone());
    }

    @Test
    void markAsDone_alreadyMarked_fail() {
        Deadline deadline = new Deadline("Homework", LocalDate.parse("2020-01-01"));
        assertTrue(deadline.markAsDone());
        assertFalse(deadline.markAsDone());
    }
}
