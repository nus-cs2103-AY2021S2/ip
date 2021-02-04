package dbot;

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
     * Runs the DBot program until an Exit command is given via the 'bye' User input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInputText = ui.getUserInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(userInputText);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point function to start the program.
     * @param args Command line arguments that were passed when executing the java file.
     */
    public static void main(String[] args) {
        new DBot("data/tasks.txt").run();
    }
}
