package duke;

import duke.command.Command;

/**
 * Represents the main logic used by the Duke chat bot.
 * It encapsulates all the functionalities such as
 * storage, the handling of tasks, Ui, as well as
 * error handling.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the Duke chat bot with all
     * of its core functionalities.
     * Attempts to load previously saved tasks' data.
     *
     * @param filePath Saved data file path.
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

    /**
     * Manages the logic of the Duke chat bot.
     */
    public void run() {
        ui.showGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.ask();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError("Oh no... " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point to the Duke chat bot.
     *
     * @param args Arguments supplied to the application.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
