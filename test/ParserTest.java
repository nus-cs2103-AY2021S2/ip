import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void trimWhiteSpaces_stringWithLeadingAndTrailingWhiteSpace_trimmedString() {
        assertEquals("df", Parser.trimWhiteSpaces("   df   "));
    }

    @Test
    void splitFirstAndRest_stringWithSpaces_firstInZeroIdxRestInFirstIdx() {
        String[] check = {"first", " have a good day"};
        String[] result = Parser.splitFirstAndRest("first have a good day");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        assertEquals(check[0], result[0]);
        assertEquals(check[1], result[1]);
    }

    @Test
    void makeToInt_notNumber_throwNumberFormatExc() {
        try {
            assertEquals(0, Parser.makeToInt("dsf"));
            fail();
        } catch (NumberFormatException e) {
            assertEquals(Ui.lineGetter()
                    + " Enter a number after command, 'done (number)' or 'delete (number)'\n"
                    + Ui.lineGetter(), e.getMessage());
        }
    }
}