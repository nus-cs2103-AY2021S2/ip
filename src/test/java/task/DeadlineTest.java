package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {


    LocalDate localDate = LocalDate.of(2021,2,1);
    Deadline deadline = new Deadline("description", localDate);

    @Test
    void testToString() {
        assertEquals("[D][ ] description (by: 2021-02-01)",deadline.toString());
    }

    @Test
    void testDone() {
        deadline.setDone(true);
        assertEquals("[D][X] description (by: 2021-02-01)",deadline.toString());
    }

}