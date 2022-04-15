import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void test1() {
        LocalDate time = LocalDate.parse("2012-12-12");
        assertEquals("[D]✘ read book (by: 2012-12-12)",
                new Deadline("read book", time).toString());
    }

    @Test
    public void test2() {
        LocalDate time = LocalDate.parse("2012-12-12");
        Deadline test = new Deadline("read book", time);
        test.markAsDone();
        assertEquals("\u2713", test.getStatusIcon());
    }
}
