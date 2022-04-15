import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testCorrectCommand() throws DukeException {
        Command c = Parser.parse("bye");
        Command c2 = Parser.parse("list");
        assertEquals(c.isBye(), true);
        assertEquals(c2.isBye(), false);
    }
}