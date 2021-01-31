import duke.command.Command;
import duke.parser.Parser;
import duke.task.*;
import duke.dukeException.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.IOException;

public class Duke {
    /** Data related object */
    private Storage storage;
    /** All tasks */
    private TaskList tasks;
    /** User interface */
    private Ui ui;

    /**
     * Class constructor.
     * Initialises the user interface and data storage.
     *
     * @param filePath  the filePath of the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Calls showWelcome to show welcome message.
     * Then gets command from user and executes the command.
     * Displays possible error message.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
