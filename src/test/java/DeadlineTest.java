import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void test_toString() {
        LocalDate time = LocalDate.parse("2012-12-12");
        assertEquals("[D]✘ read book (by: 2012-12-12)",
                new Deadline("read book", time).toString());
    }
}
