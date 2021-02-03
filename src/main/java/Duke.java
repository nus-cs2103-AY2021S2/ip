/**
 * Driver class. Creating a Duke object runs the application.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Duke constructor specifying the file path to save the task list to.
     * @param filePath File path to save task list to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.readFromStorage(taskList);
    }

    /**
     * Runs Duke application.
     */
    public void run() {
        ui.runUi(taskList, storage);
    }

    /**
     * Driver code. Creates and runs Duke.
     * @param args Commandline arguments. Not used. 
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
