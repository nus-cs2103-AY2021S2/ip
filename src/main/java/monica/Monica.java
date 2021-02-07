package monica;

import monica.command.Command;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents the main class of the application <code>Duke</code>.
 */
public class Monica {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String filePath = "src/main/java/data/tasks.txt";

    /**
     * Constructor for Duke class that initiates task storage locally.
     */
    public Monica() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.openFile();
        } catch (MonicaException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Executes the command lines from a user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (MonicaException e) {
            return ui.showError(e);
        }
    }

}
