package duke;

import duke.controller.ListController;
import duke.controller.Parser;
import duke.controller.StorageController;
import duke.controller.UiController;
import duke.model.exception.DukeException;
import duke.model.task.TaskList;
import duke.view.Gui;
import javafx.application.Application;

public class Duke {
    private TaskList tasks;
    private Parser in;
    private final UiController uiController;
    private final ListController listController;
    private final StorageController storageController;

    /**
     * Initiate Duke with default Parser, TaskList and Controllers for it to function
     */
    public Duke() {
        in = new Parser();
        tasks = new TaskList();
        storageController = new StorageController(this, "dukeData.txt");
        try {
            tasks = new TaskList(storageController.loadData());
        } catch (DukeException.InputOutputErrorException e) {
            tasks = new TaskList();
        }
        uiController = new UiController(this, tasks);
        listController = new ListController(this, tasks);
    }

    public UiController getUiController() {
        return uiController;
    }

    public ListController getListController() {
        return listController;
    }

    public StorageController getStorageController() {
        return storageController;
    }

    /**
     * Initiate the GUI interface
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
