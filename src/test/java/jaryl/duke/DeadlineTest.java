package jaryl.duke;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

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
