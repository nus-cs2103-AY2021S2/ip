import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test_toString() {
        LocalDateTime time = LocalDateTime.parse("2012-12-12T12:12");
        assertEquals("[E]✘ read book (at: 2012-12-12 12:12)",
                new Event("read book", time).toString());
    }
}
