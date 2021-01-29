import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineTaskTest {
    @Test
    public void Test1() {
        DeadlineTask dTask = new DeadlineTask("deadline do assignment /by 2021-03-15");
        String dTaskString = dTask.toString();
        assertEquals("[D][ ] do assignment (by: Mar 15 2021)", dTaskString);
    }

    @Test
    public void Test2() {
        DeadlineTask dTask = new DeadlineTask("deadline do assignment /by 2021-03-15");
        dTask.markDone();
        String dTaskString = dTask.toString();
        assertNotEquals("[D][ ] do assignment (by: Mar 15 2021)", dTaskString);
    }
}
