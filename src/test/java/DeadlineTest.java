import main.java.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion_properlyFormattedDate() {
        assertEquals("[D][ ] Do CS2103T iP (by: Feb 19 2021)",
            new Deadline("Do CS2103T iP", "2021-02-19").toString());
    }

    @Test
    public void testStringConversion_dateNotInExpectedFormat() {
        assertEquals("[D][ ] Do CS2103T iP (by: 19/02/2021)",
            new Deadline("Do CS2103T iP", "19/02/2021").toString());
    }
}
