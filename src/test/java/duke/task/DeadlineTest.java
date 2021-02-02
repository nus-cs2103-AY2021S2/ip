package duke.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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
