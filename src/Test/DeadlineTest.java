import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringCoversion() {
        assertEquals("[D][INPROGRESS] read book (by: Dec 2 2019)",
                new Deadline("read book", LocalDate.parse("2019-12-02")).toString());
    }
}