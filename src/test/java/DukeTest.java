import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;


public class DukeTest {
    @Test
    public void storageTest() {
        Storage storage = new Storage("./data.txt");
        storage.readFile();
    }

    @Test
    public void taskListTest() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.delete(2);
    }
}
