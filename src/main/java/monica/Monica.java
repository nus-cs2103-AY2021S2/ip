package monica;

import monica.command.Command;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents the read-and-respond action of the application <code>Monica</code>.
 */
public class Monica {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Constructor for Monica class that initiates task storage locally.
     */
    public Monica() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = storage.openFile();
        } catch (MonicaException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Responds to the command lines from a user.
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
