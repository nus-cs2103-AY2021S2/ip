import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals("[E][ ] example event task (05 Jan 2021 to 06 Jan 2021)",
                new Event("example event task", LocalDate.parse("2021-01-05"),
                        LocalDate.parse("2021-01-06")).toString());
    }

    @Test
    public void getDataTest() {
        assertEquals("E!@#0!@#example event task!@#2021-01-05!@#2021-01-06",
                new Event("example event task", LocalDate.parse("2021-01-05"),
                        LocalDate.parse("2021-01-06")).getData());
    }
}
