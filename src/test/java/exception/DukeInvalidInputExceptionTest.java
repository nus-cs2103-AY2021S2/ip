package exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DukeInvalidInputExceptionTest {

    @Test
    public void invalidInputExceptionIsOfTypeDukeException() {
        assertTrue(new DukeInvalidInputException() instanceof DukeException);
    }

    @Test
    public void invalidInputExceptionPopulatesFields() {
        DukeInvalidInputException exception = new DukeInvalidInputException(
                "testInvalidInputCommand with /invalid arguments");

        assertEquals("testInvalidInputCommand with /invalid arguments", exception.getInvalidInput());
        assertEquals("Invalid Input: testInvalidInputCommand with /invalid arguments", exception.getMessage());
    }
}
