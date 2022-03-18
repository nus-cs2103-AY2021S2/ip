import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ip.src.main.java.Deadline;

public class DeadlineTest {
    @Test

    /*
      Tests the toString() method of the Deadline class to see if deadline object is represented as expected.
     */
    public void deadlineToStringTest() {
        Deadline deadline = new Deadline("Test" , "12/2/2019 18:00");
        String deadlineToString = deadline.toString();
        String expectedString = "D | 0 | " + "Test" + " | " + "12/02/2019 18:00";
        assertEquals(expectedString , deadlineToString);
    }
}
