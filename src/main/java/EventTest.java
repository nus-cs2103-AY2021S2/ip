import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class that conducts JUnit test on the methods from Event class.
 */
class EventTest {
    /**
     * A test for the toString() method in Event class.
     */
    @Test
    void testToString() {
        assertEquals("[E][ ] read book (at: 2019-01-02 12:45)",
                new Event("read book", LocalDate.parse("2019-01-02"), LocalTime.parse("12:45")).toString());
    }
}