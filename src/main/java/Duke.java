import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.readFromStorage(taskList);
    }

    public void run() {
        ui.runUi(taskList, storage);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
