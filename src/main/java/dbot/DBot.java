package dbot;

import com.sun.glass.ui.Application;
import dbot.command.Command;
import dbot.exception.DBotException;
import dbot.parser.Parser;
import dbot.storage.Storage;
import dbot.tasklist.TaskList;
import dbot.ui.Ui;

/**
 * Entry point of the DBot application.
 * Initializes the application and starts the interaction with the user.
 */
public class DBot {

    /* Handles IO */
    private Storage storage;

    /* Stores all current tasks */
    private TaskList tasks;

    /* Interacts with the User */
    private Ui ui;

    /**
     * Initialises the application by instantiating the necessary programs.
     * If a text file at filePath exists, it loads the stored tasks.
     *
     * @param filePath A String path leading to a text file that has stored Tasks.
     */
    public DBot(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = storage.load();
        } catch (DBotException e) {
            ui.showLoadingError();
            tasks = new TaskList(100);
        }
    }

    /**
     * Initialises the application by instantiating the necessary programs.
     * If a text file at the default filePath exists, it loads the stored tasks.
     * The default filePath is specified in Storage.java.
     */
    public DBot() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = storage.load();
        } catch (DBotException e) {
            ui.showLoadingError();
            tasks = new TaskList(100);
        }
    }

    /**
     * Runs the DBot program until an Exit command is given via the 'bye' User input.
     */
    public String getResponse() {
        String response;
        try {
            String userInputText = ui.getUserInput();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(userInputText);
            response = c.execute(tasks, ui, storage);
        } catch (DBotException e) {
            response = ui.showError(e.getMessage());
        } finally {
            response = ui.showLine();
        }
    }

    public String getResponse(String input) {
    }
}
