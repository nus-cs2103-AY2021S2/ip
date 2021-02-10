package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.tasks.Storage;
import duke.tasks.TaskList;
import duke.ui.Parser;

/**
 * The chatbot/to-do list application.
 */
public class Duke {
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Initializes an instance of Duke with a <code>TaskList</code> property, which is essentially the
     * state of the application, and a <code>Storage</code> property, which handles the loading and saving
     * of tasks when the application starts and shuts down respectively.
     *
     * @param fileName Name of text file in directory data/ from which tasks are loaded when the app starts, and to
     *                 which tasks are saved when the app terminates.
     *                 If the text file does not already exist in the directory data/, then the app starts
     *                 with an empty <code>TaskList</code>. When the app terminates, a new text file
     *                 with the input file name will be created, to which existing tasks are saved.
     */
    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Executes the command and computes a response to display to the users w.r.t.
     * the users' input.
     *
     * @param input A line of raw user input.
     * @return A <code>String</code> of response.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        command.execute(this.tasks);

        if (command instanceof ByeCommand) {
            this.storage.saveTasks(this.tasks);
        }

        return command.getResponse(this.tasks);
    }

    /**
     * Determines if the application should be exited w.r.t the users' input.
     *
     * @param input A line of raw user input.
     * @return true if the application should be terminated, and false otherwise.
     */
    public boolean isExit(String input) {
        Command command = Parser.parse(input);
        return command.isExit();
    }
}
