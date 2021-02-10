package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.tasks.Storage;
import duke.tasks.TaskList;
import duke.ui.Parser;

public class Duke {
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Initializes an instance of Duke to handle ALL the logic of the application.
     *
     * @param filepath Path to text file from which tasks are loaded when the app starts, and to
     *                 which tasks are saved when the app terminates.
     *                 If a text file does not already exist at the given path, then the app starts
     *                 with an empty <code>TaskList</code>. When the app terminates, a new text file
     *                 corresponding to the input path will be created, to which existing tasks are saved.
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Executes the command w.r.t. the users' input.
     *
     * @param input A line of raw user input.
     */
    public void execute(String input) {
        Command command = this.getCommand(input);
        command.execute(this.tasks);

        if (command instanceof ByeCommand) {
            this.storage.saveTasks(this.tasks);
        }
    }

    /**
     * Computes a response to display to the users w.r.t. the users' input.
     *
     * @param input A line of raw user input.
     * @return A <code>String</code> of response.
     */
    public String getResponse(String input) {
        Command command = this.getCommand(input);
        return command.getResponse(this.tasks);
    }

    /**
     * Determines if the application should be exited w.r.t the users' input.
     *
     * @param input A line of raw user input.
     * @return true if the application should be terminated, and false otherwise.
     */
    public boolean isExit(String input) {
        Command command = this.getCommand(input);
        return command.isExit();
    }

    /**
     * Gets the <code>Command</code> object w.r.t the users' input.
     *
     * @param input A line or raw user input.
     * @return A <code>Command</code> object corresponding to the input.
     */
    private Command getCommand(String input) {
        return Parser.parse(input);
    }
}
