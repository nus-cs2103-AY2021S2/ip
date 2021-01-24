package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the required objects.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.loadTasksFromFile(tasks);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the inputs entered by the user,
     * until the user enters the exit command.
     */
    public void handleUserInput() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.nextUserInput();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isRunning = !command.isExitCommand();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.printGreeting();
        handleUserInput();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
