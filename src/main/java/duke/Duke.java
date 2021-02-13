package duke;

import duke.command.Command;
import duke.command.CommandResponse;
import duke.task.Task;

import java.util.ArrayList;

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

    public Duke() {
        this("data/tasks.txt");
    }

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

    public String welcomeUser() {
        return ui.showGreetings();
    }

    public ArrayList<Task> getTasks() {
        return tasks.getList();
    }

    public CommandResponse getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert this.storage != null;
            assert this.tasks != null;
            assert this.ui != null;
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return new CommandResponse("Oh no... " + e.getMessage(), false);
        }
    }
}
