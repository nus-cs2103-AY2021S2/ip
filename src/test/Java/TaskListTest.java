import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void checkValidOption_lessThanZero_returnTrue() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertTrue(taskList.checkValidOption(-1));
    }
}
