import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.StringParser;

public class StringParserTest {
    @Test
    public void newLiner_shortString() {
        assertEquals("bye\n",
                StringParser.newLiner("bye", 5));

    }

    @Test
    public void newLiner_longString() {
        assertEquals("11111\n22222\n33333\n44444\n555\n",
                StringParser.newLiner("11111222223333344444555", 5));
    }

    @Test
    public void newLiner_lengthEqualOne() {
        assertEquals("1\n2\n3\n4\n5\n",
                StringParser.newLiner("12345", 1));
    }

    @Test
    public void newLiner_specialString() {
        assertEquals("1\n2\n345\n",
                StringParser.newLiner("1\n2345", 3));
    }

    @Test
    public void newLiner_lengthEqualZero_exceptionThrown() {
        try {
            assertEquals("1\n2\n3\n4\n5\n",
                    StringParser.newLiner("12345", 0));
            fail();
        } catch (AssertionError e) {
            assertEquals(AssertionError.class, e.getClass());
        }
    }

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
