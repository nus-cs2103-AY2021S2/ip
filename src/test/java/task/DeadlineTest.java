package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {


    Date localDate = new Date(0);
    Deadline deadline = new Deadline("description", localDate);

    @Test
    void testToString() {
        assertEquals("[D][ ] description (by: Thu Jan 01 07:30:00 SGT 1970)",deadline.toString());
    }

    @Test
    void testDone() {
        deadline.setDone(true);
        assertEquals("[D][X] description (by: Thu Jan 01 07:30:00 SGT 1970)",deadline.toString());
    }

}