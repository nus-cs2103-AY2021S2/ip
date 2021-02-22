package quackers;

import quackers.command.Command;
import quackers.command.CommandResponse;
import quackers.parser.CommandParser;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

/**
 * Represents the main logic used by Quackers.
 */
public class Quackers {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs Quackers object with a fixed data file path.
     */
    public Quackers() {
        this("data/tasks.txt");
    }

    /**
     * Constructs Quackers object by specifying data file path.
     *
     * @param filePath Path to data file.
     */
    public Quackers(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (QuackersException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Parses a raw command input text, executes the relevant Command and returns the response.
     *
     * @param input Raw command input text.
     * @return CommandResponse object.
     */
    public CommandResponse getResponse(String input) {
        try {
            Command c = CommandParser.getCommand(input);
            assert this.storage != null;
            assert this.tasks != null;
            return c.getResponse(this.tasks, this.storage);
        } catch (QuackersException e) {
            String message = Ui.getErrorMessage(e.getMessage());
            return new CommandResponse(null, message, false);
        }
    }

    /**
     * Retrieves the Ui greeting.
     *
     * @return Ui greeting.
     */
    public String getGreeting() {
        return Ui.getGreeting();
    }

    /**
     * Retrieves the TaskList object.
     *
     * @return TaskList object.
     */
    public TaskList getTaskList() {
        return this.tasks;
    }
}
