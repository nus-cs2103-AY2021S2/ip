package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The Duke class serves as the main entry point to the program.
 * Duke is a personal assistant chat bot that helps a person to
 * keep track of various tasks. It uses a CLI to interact with
 * the user and allows users to create, update, delete tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of a Duke object.
     * Will attempt to load previously saved tasks from a file.
     * @param pathname The pathname of the file for local data storage.
     */
    public Duke(String pathname) {
        ui = new Ui();
        storage = new Storage(pathname);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Starts running the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parseCommand(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
