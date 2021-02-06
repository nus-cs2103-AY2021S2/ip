package duke;

import duke.command.DoneCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void checkValidTaskCommand() {
        String dummyInput = "hello";
        assertEquals(Parser.isValidTaskCommand(dummyInput), false);
    }

}
