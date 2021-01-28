import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Starts the program.
     * 
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Initialization of the Storage, TaskList and Ui.
     * 
     * @param filePath The file path for storage of data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
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

}
