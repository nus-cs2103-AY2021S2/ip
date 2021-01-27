package duke;

import duke.exceptions.DukeException;
import duke.util.Storage;

import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The Apollo bot handling Storage, TaskList and Ui.
 */
public class Apollo {
    private static final String BOT_NAME = "Apollo the Robot";
    private static final String STORAGE_PATH = "data/ApolloTaskData.txt";
    private TaskList taskList;
    private final Storage storageHandler;
    private final Ui ui;

    /**
     * Constructor for Apollo.
     */
    public Apollo() {
        this.storageHandler = new Storage(STORAGE_PATH);
        this.ui = new Ui();

        try {
            this.taskList = new TaskList(this.storageHandler.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }

        ui.displayWelcomeText(BOT_NAME);
        ui.startInputManager(this);
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
}
