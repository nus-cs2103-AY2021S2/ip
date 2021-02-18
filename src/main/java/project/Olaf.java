package project;

import java.io.IOException;

import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

// @@author: VRSoorya
// Adapted from GitHub repo nus-cs2103-AY2021S2/ip

/**
 * Initialises each session of the application.
 */
public class Olaf {
    public static final String FILE_PATH = "./data/olaf.txt";

    private OlafApp app;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of {@code Olaf}.
     */
    public Olaf() {
        storage = new Storage(FILE_PATH);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }

        app = new OlafApp(tasks, ui, storage);
    }

    /**
     * Responds to user input.
     */
    public String getResponse(String text) {
        return app.run(text);
    }
}
