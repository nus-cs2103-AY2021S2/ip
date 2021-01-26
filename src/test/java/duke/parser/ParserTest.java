package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void isValidTaskNumberTest(){
        Parser p = new Parser();

        assertEquals(false,p.isValidTaskNumber(-1, "done", 0));
        assertEquals(false,p.isValidTaskNumber(-1, "delete", 0));
        assertEquals(true,p.isValidTaskNumber(3, "done", 10));
    }
}
