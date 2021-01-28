import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void trimWhiteSpaces() {
        assertEquals("df", Parser.trimWhiteSpaces("   df   "));
    }

    @Test
    void firstAndRest_stringWithSpaces_firstInZeroIdxRestInFirstIdx() {
        String[] check = {"first", " have a good day"};
        String[] res = Parser.firstAndRest("first have a good day");
        for(int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
        assertEquals(check[0], res[0]);
        assertEquals(check[1], res[1]);
    }

    @Test
    void makeToInt_notNumber_throwNumberFormatExc() {
        try {
            assertEquals(0, Parser.makeToInt("dsf"));
            fail();
        } catch (NumberFormatException e) {
            assertEquals(Ui.lineGetter() +
                    " Enter a number after command, 'done (number)' or 'delete (number)'\n"
                    + Ui.lineGetter(), e.getMessage());
        }
    }
}