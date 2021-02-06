package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTaskTest {

    @Test
    void getDeadlineDate_validDate_success() {
        DeadlineTask deadlineTask = new DeadlineTask("homework", "2021-01-01 19:00");
        LocalDate validDate = LocalDate.parse("2021-01-01");
        assertEquals(deadlineTask.getDeadlineDate(), validDate);
    }

    @Test
    void getDeadlineDate_invalidDate_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            DeadlineTask deadlineTask = new DeadlineTask("homework", "01-01-2021 19:00");
            deadlineTask.getDeadlineDate();
        });
    }

    @Test
    void getDeadlineTime_validTime_success() {
        DeadlineTask deadlineTask = new DeadlineTask("homework", "2021-01-01 19:00");
        LocalTime validTime = LocalTime.parse("19:00");
        assertEquals(deadlineTask.getDeadlineTime(), validTime);
    }

    @Test
    void getDeadlineTime_invalidTime_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            DeadlineTask deadlineTask = new DeadlineTask("homework", "01-01-2021 25:00");
            deadlineTask.getDeadlineTime();
        });
    }

    @Test
    void toString_validInputs_success() {
        assertEquals(new DeadlineTask("homework", "2021-01-01 19:00").toString(),
                "[D][ ] homework (by: Jan 1 2021, 7:00PM)");
    }
}