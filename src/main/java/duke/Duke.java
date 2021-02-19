package duke;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * The Duke bot handling Storage, TaskList and Ui.
 */
public class Duke {

    private static final String BOT_NAME = "Apollo the Robot";
    private static final String STORAGE_PATH = "data/ApolloTaskData.txt";
    private TaskList taskList;
    private final Storage storageHandler;
    private final Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storageHandler = new Storage(STORAGE_PATH);
        this.ui = new Ui();

        try {
            this.taskList = new TaskList(this.storageHandler.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }

        ui.displayWelcomeText(BOT_NAME);
        // ui.startInputManager(this);
    }

    /**
     * Gets Apollo's TaskList.
     * @return Apollo's TaskList.
     */
    public TaskList getTasks() {
        return taskList;
    }

    /**
     * Saves the user's TaskList data to persistent storage.
     */
    public void saveData() {
        try {
            storageHandler.writeFile(taskList.getTaskList());
        } catch (IOException | DukeException e) {
            Ui.showMessageBetweenLines(e.getMessage());
        }
    }

    /**
     * Gets a response from a command execution to be displayed in the GUI.
     * @param input User input that is to be parsed.
     * @return String with Duke's response to a user's input.
     */
    String getResponse(String input) {
        ui.handleInput(this, input);
        return ui.getMessages();
    }
}
