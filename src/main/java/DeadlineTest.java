import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeadlineTest {
    @Test
    void testToString() {
        assertEquals("[D][ ] read book (by: 2019-01-02 12:45)"
                , new Deadline("read book", LocalDate.parse("2019-01-02"), LocalTime.parse("12:45")).toString());
    }
}