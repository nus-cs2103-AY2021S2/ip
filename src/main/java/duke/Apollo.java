package duke;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Apollo {

    private static final String BOT_NAME = "Apollo the Robot";
    private static final String STORAGE_PATH = "data/ApolloTaskData.txt";
    private TaskList taskList;
    private final Storage storageHandler;
    private final Ui ui;

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

    public TaskList getTasks() {
        return taskList;
    }

    public void saveBeforeExit() {
        try {
            storageHandler.writeFile(taskList.getTaskList());
        } catch (IOException | DukeException e) {
            Ui.showMessageBetweenLines(e.getMessage());
        }
    }
}
