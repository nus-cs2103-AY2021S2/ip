package duke;

import duke.command.Command;
import duke.exception.DukeStorageException;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;

/**
 * Class representing chat bot, Duke.
 */
public class Duke {
    private TaskList tasks;
    private TaskStorage storage;
    private Ui ui;
    private boolean isActive;

    /**
     * Constructor of Duke.
     */
    public Duke() throws DukeStorageException {
        ui = new Ui();
        storage = new TaskStorage("data/tasks.txt");
        tasks = storage.retrieveData();
        isActive = true;
    }

    public String getResponse(String input) {
        if (input.isBlank()) {
            return "You have not entered a command!";
        }
        Command cmd = Parser.parse(input, tasks);
        String response = cmd.execute(tasks, ui, storage);
        isActive = !cmd.toExit();
        return response;
    }

    public boolean isActive() {
        return isActive;
    }
}
