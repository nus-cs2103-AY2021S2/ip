import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void parse_emptyInput_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Deadline.parse("");
        });

        String expected = "Deadline description cannot be empty";
        String actual = exception.getMessage();
        assertEquals(actual, expected);
    }
}
