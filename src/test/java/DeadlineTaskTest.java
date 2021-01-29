import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineTaskTest {
    @Test
    public void Test1() {
        DeadlineTask dTask = new DeadlineTask("deadline do assignment /by 2021-03-15");
        assertEquals("[D][ ] do assignment (by: Mar 15 2021)", dTask);
    }

    @Test
    public void Test2() {
        DeadlineTask dTask = new DeadlineTask("deadline do assignment /by 2021-03-15");
        dTask.markDone();
        assertNotEquals("[D][X] do assignment (by: Mar 15 2021)", dTask);
    }
}
