package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class for Duke application.
 */
public class Duke {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Initializes a Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage();
        try {
            storage.readFile(tasks);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Starts reading inputs and executing Duke's program.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
