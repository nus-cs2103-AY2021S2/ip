import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initialization of the Storage, TaskList and Ui.
     * @param filePath The file path for storage of data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
            this.ui.showLoadingError();
        }
    }

    /**
     * Starts the program to handle user interaction.
     */
    public void run() {
        Parser p = new Parser(storage, tasks, ui);
        p.open();
        p.close();
    }

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

}
