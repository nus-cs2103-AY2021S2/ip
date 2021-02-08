package duke;

import duke.commands.Command;
import duke.tasks.Storage;
import duke.tasks.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

/**
 * Represents the chatbot/to-do list app.
 */
public class Duke {
    private final Ui ui;

    /**
     * Initializes a chatbot/to-do list app with a user-interface property that
     * handles all user interactions.
     */
    public Duke() {
        this.ui = new Ui();
    }

    /**
     * Runs the initialized chatbot app. Saved tasks are loaded (if any). When the app
     * terminates, any existing tasks will be saved.
     *
     * @param filePath Path to text file from which tasks are loaded when the app starts, and to
     *                 which tasks are saved when the app terminates.
     *                 If a text file does not already exist at the given path, then the app starts
     *                 with an empty <code>TaskList</code>. When the app terminates, a new text file
     *                 corresponding to the input path will be created, to which existing tasks are saved.
     */
    public void run(String filePath) {
        Storage storage = new Storage(filePath);
        TaskList tasks = storage.loadTasks();

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = Parser.readInput();
            Command c = Parser.parse(input);
            c.execute(tasks, this.ui);
            isExit = c.isExit();
        }

        storage.saveTasks(tasks);
    }

    public static void main(String[] args) {
        new Duke().run("data/tasks.txt");
    }
}
