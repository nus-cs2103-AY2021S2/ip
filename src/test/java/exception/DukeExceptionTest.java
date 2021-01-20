package exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {

    @Test
    public void dukeExceptionIsOfTypeException() {
        assertTrue(new DukeException() instanceof Exception);
    }

}
