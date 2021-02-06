import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Printer;
import duke.Storage;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;


public class DukeTest {
    @Test
    public void storageTest() {
        Storage storage = new Storage("./data.txt");
        storage.readFile();
    }

    @Test
    public void taskListTest() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.deleteTask(2);
    }

    @Test
    public void printerTest() {
        System.out.println(Printer.printAddReply(new ToDo("CS2103", TaskType.TODO), 2));
    }
}
