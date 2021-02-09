package duke;

import duke.commands.Command;
import duke.commands.CommandResponse;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/** Duke is a Personal Assistant Chat Bot that helps a person to keep track of various things.
 * Initialize the application and start interacting with users.
 */

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /** Initialize the required objects and tries to loads up the data from the storage file,
     * otherwise a new storage file will be created.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses the user's input and executes its command.
     *
     * @param input input from user
     */
    public CommandResponse getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return new CommandResponse(ui.showError(e.getMessage()));
        }
    }
}
