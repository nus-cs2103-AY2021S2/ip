import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.StringParser;

public class StringParserTest {

    @Test
    public void underlineGenerator_zeroRepeat() {
        assertEquals("\n",
                StringParser.generateUnderline(0));
    }

    @Test
    public void isBlank_zeroString() {
        assertTrue(StringParser.isBlank(""));
    }

    @Test
    public void isBlank_blank() {
        assertFalse(StringParser.isBlank("  aaa   "));
    }

    @Test
    public void isBlank_specialString() {
        assertFalse(StringParser.isBlank("\n\n\n\n\n  "));
    }
}
