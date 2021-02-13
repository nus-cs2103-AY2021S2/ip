package monica;

import monica.command.Command;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents the read-and-respond action of the application <code>Monica</code>.
 */
public class Monica {
    private final Storage STORAGE;
    private final Ui UI;
    private static final String FILE_PATH = "data/tasks.txt";
    private TaskList tasks;

    /**
     * Constructor for Monica class that initiates task storage locally.
     */
    public Monica() {
        UI = new Ui();
        STORAGE = new Storage(FILE_PATH);
        try {
            tasks = STORAGE.openFile();
        } catch (MonicaException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Responds to the command lines from a user.
     */
    public String getResponse(String input) {
        assert input != null : "Command is missing.";
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, UI, STORAGE);
        } catch (MonicaException e) {
            return UI.showError(e);
        }
    }

}
