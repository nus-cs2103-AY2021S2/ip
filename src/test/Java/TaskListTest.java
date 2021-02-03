import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void checkValidOption_lessThanZero_returnTrue() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertTrue(taskList.checkValidOption(-1));
    }
}
