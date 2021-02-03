package project;

import project.common.PrintText;
import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

import java.io.IOException;

// @@author: VRSoorya
// adapted from GitHub repo nus-cs2103-AY2021S2/ip

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
            ui.showFormatResponse(PrintText.WELCOME_MESSAGE);
        } catch (IOException e) {
            ui.showLoadingError();
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
