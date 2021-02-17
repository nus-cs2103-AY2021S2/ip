package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void isValidTaskNumberTest(){
        Parser parser = new Parser();

        assertEquals(false,parser.isValidTaskNumber(-1, "done", 0));
        assertEquals(false,parser.isValidTaskNumber(-1, "delete", 0));
        assertEquals(true,parser.isValidTaskNumber(3, "done", 10));
    }
}
