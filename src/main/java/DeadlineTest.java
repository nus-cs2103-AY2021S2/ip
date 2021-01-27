import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class that conducts JUnit test on the methods from Deadline class.
 */
class DeadlineTest {
    /**
     * A test for the toString() method in Deadline class.
     */
    @Test
    void testToString() {
        assertEquals("[D][ ] read book (by: 2019-01-02 12:45)"
                , new Deadline("read book", LocalDate.parse("2019-01-02"), LocalTime.parse("12:45")).toString());
    }
}