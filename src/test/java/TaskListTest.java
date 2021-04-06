import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskListTest {

    @Test
    public void testMarkAsDone() throws IOException {
        Storage storage = new Storage();
        List<Task> taskList = storage.loadFile();
        TaskList tl = new TaskList(taskList);
        System.out.println("Before:");
        tl.enumerateTasks();
        for (Task eachTask : tl.taskList) {
            eachTask.markAsDone();
        }
        System.out.println("After Marking all as done:");
        tl.enumerateTasks();

    }

}
