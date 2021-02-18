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
     * Returns greeting message String.
     * @return greeting message String
     */
    public String greet() {
        return ui.getGreeetingMsg();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getError(e.getMessage());
        }
    }
}
