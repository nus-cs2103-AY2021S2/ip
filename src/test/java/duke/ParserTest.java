package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void checkValidTaskCommand() {
        String dummyInput = "hello";
        assertEquals(Parser.isValidTaskCommand(dummyInput), false);
    }

}
