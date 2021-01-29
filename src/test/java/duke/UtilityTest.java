package duke;

import static duke.Utility.isNumeric;
import static duke.Utility.isValidDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UtilityTest {
    @Test
    public void isNumericTest() {
        assertEquals(true, isNumeric("10"));
        assertEquals(true, isNumeric("-1"));
        assertEquals(false, isNumeric("ten"));
        assertEquals(false, isNumeric("10sad10"));
    }

    @Test
    public void isValidDateTest() {
        assertEquals(true, isValidDate("2018-10-02"));
        assertEquals(false, isValidDate("02-10-2019"));
        assertEquals(false, isValidDate("21 October 2021"));
        assertEquals(false, isValidDate("2018-10-02 19sx"));
    }
}
