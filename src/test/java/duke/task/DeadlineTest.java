package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void serialise_isNotDone_success() {
        Deadline deadline = new Deadline("Description", "2021-01-01 16:00");
        assertEquals("DEADLINE|false|Description|2021-01-01|16:00", deadline.serialise());
    }
}
