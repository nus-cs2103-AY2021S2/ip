import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;


public class DateValidationTest {

    @Test
    public void handleDate_invalidInput_exceptionThrown() {
        try {
            assertEquals(LocalDate.parse("2021-01-23"), DateValidation.handleDate("23-01-2021"));
            fail();
        } catch (DukeException e) {
            assertEquals(":( Date format is invalid! Please enter in yyyy-mm-dd format!",
                    e.getMessage());
        }
    }


}
