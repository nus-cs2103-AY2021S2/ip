import org.junit.jupiter.api.Test;

import duke.Printer;
import duke.Storage;
import duke.task.TaskList;
import duke.task.ToDo;


public class DukeTest {
    @Test
    public void storageTest() {
        Storage storage = new Storage("./data.txt");
        storage.readFile();
    }

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("CS2103T"));
    }

    @Test
    public void printerTest() {
        System.out.println(Printer.printAddReply(new ToDo("CS2103"), 2));
    }
}
