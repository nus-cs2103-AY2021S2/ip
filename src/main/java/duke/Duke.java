package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.tasks.Storage;
import duke.tasks.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

public class Duke {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Initializes an instance of the Duke chatbot/todo list.
     *
     * @param filepath Path to text file from which tasks are loaded when the app starts, and to
     *                 which tasks are saved when the app terminates.
     *                 If a text file does not already exist at the given path, then the app starts
     *                 with an empty <code>TaskList</code>. When the app terminates, a new text file
     *                 corresponding to the input path will be created, to which existing tasks are saved.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Get response
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(this.tasks);

        if (c instanceof ByeCommand) {
            storage.saveTasks(this.tasks);
        }

        return c.getResponse(this.tasks, this.ui);
    }
}
