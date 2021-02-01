import ip.src.main.java.Deadline;
import ip.src.main.java.Duke;
import ip.src.main.java.Task;
import ip.src.main.java.ToDo;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    /**
     * Tests the toString() method of the Deadline class to see if deadline object is represented as expected.
     */
    public void deadlineToStringTest(){
        Deadline deadline = new Deadline("Test","12/2/2019 18:00");
        String deadlineToString = deadline.toString();
        String expectedString =  "D | 0 | " + "Test" + " | " + "12/02/2019 18:00";
        assertEquals(expectedString, deadlineToString);

    }
}
