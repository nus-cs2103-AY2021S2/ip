package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void getSavingString_undoneDeadlineTasks_success() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ldt = LocalDateTime.parse("2020-01-01 1800", dtf);
        DeadlineTask dlTask = new DeadlineTask("test undone Deadline", ldt);
        assertEquals("DEADLINE|0|test undone Deadline|2020-01-01 1800\n", dlTask.getSavingString());
    }

    @Test
    public void getSavingString_doneDeadlineTasks_success() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ldt = LocalDateTime.parse("2020-01-01 1800", dtf);
        DeadlineTask dlTask = new DeadlineTask("test done Deadline", ldt);
        LocalDateTime currTime = LocalDateTime.now();
        dlTask.markDonePast(currTime);
        String expectedOutput = "DEADLINE|1|test done Deadline|2020-01-01 1800|"
                + currTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
        assertEquals(expectedOutput, dlTask.getSavingString());
    }
}
