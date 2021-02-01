package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Duke is a Personal Assistant Chat Bot that helps a person to keep track of various things.
 * Initialize the application and start interacting with users.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialize the required objects and tries to loads up the data from the storage file,
     * otherwise a new storage file will be created.
     *
     * @param filePath the path to the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Prints welcome message and reads the user inputs and execute the respective command.
     * Exit when the user types 'bye'.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
