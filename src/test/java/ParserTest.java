import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseCommandType() {
        String command = "todo read book";
        String actual = Parser.parseCommandType(command);
        assertEquals("todo", actual);
    }

    @Test
    public void testParseTaskIndex() {
        String command = "done 1";
        String[] tokenizedCommand = command.split(" ");
        int actual = Integer.parseInt(tokenizedCommand[1]);
        assertEquals(1, actual);
    }

}
