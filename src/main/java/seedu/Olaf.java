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

    /**
     * Creates an instance of Olaf.
     *
     * @param filePath String specifying the path of the file used to save and load data.
     */
    public Olaf(String filePath) {
        Storage storage = new Storage(filePath);

        Ui ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the application with OlafApp.
     */
    public void run() {
        new OlafApp(tasks).run();
    }
    
    /**
     * Starts the application with OlafApp.
     *
     * @param args path of file to save and load application data.
     */
    public static void main(String[] args) {
        new Olaf("./data/olaf.txt").run();
    }
}
