
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void toStringTest() {
        Task task = Parser.parseFileInput("D | 0 | return book | Dec 2 2019 6:00 PM");
        assertEquals("[D][ ] return book (by: Dec 2 2019 6:00 PM)", task.toString());
    }

}
