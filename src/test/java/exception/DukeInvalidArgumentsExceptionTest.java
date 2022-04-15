package exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DukeInvalidArgumentsExceptionTest {

    @Test
    public void invalidArgumentsExceptionIsOfTypeDukeException() {
        assertTrue(new DukeInvalidArgumentsException("test", "test") instanceof DukeException);
    }

    @Test
    public void invalidArgumentsExceptionPopulatesField() {
        DukeInvalidArgumentsException exception = new DukeInvalidArgumentsException("testCommand",
                "Test Error Message");
        assertEquals("testCommand", exception.getCommand());
        assertEquals("Test Error Message", exception.getError());
        assertEquals("Command testCommand encountered invalid arguments: Test Error Message", exception.getMessage());
    }
}
