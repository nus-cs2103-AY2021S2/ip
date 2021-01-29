package seedu;

import seedu.io.Ui;
import seedu.storage.Storage;
import seedu.task.TaskList;

import java.io.IOException;

// @@author: VRSoorya
// adapted from GitHub repo nus-cs2103-AY2021S2/ip

/**
 * Initialises each session of the application.
 */
public class Olaf {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of {@code Olaf}.
     *
     * @param filePath Path of local file to save to and load tasks from.
     */
    public Olaf(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts a session of the application.
     */
    public void run() {
        new OlafApp(tasks, ui, storage).run();
    }

    /**
     * Main method of the application.
     */
    public static void main(String[] args) {
        new Olaf("./data/olaf.txt").run();
    }
}
