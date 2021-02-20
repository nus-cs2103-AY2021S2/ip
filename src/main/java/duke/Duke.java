package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents a task manager that allows users to add, delete and mark tasks as done.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of <code>Duke</code> with given filePath.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }


    /**
     * Gets response from duke to be printed to user.
     *
     * @param input User input.
     * @return Output string.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | IOException | DateTimeParseException e) {
            return e.getMessage();
        }
    }

}
