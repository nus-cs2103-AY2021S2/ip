package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    private LocalDate localDate = LocalDate.of(2021, 2, 1);
    private Deadline deadline = new Deadline("description", localDate);

    @Test
    void testToString() {
        assertEquals("[D][ ] description (by: 2021-02-01)", deadline.toString());
    }

    @Test
    void testDone() {
        deadline.setDone(true);
        assertEquals("[D][X] description (by: 2021-02-01)", deadline.toString());
    }

}
