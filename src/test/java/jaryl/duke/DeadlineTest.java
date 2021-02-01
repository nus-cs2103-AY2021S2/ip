package jaryl.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        try {
            Deadline deadline = new Deadline("Math Homework", "02/12/2021 1800");
            assertEquals(deadline.toString(), "[D][ ] Math Homework (by: 02 Dec 2021 1800)");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
