package duke;

import duke.controller.ListController;
import duke.controller.Parser;
import duke.controller.UiController;
import duke.model.task.TaskList;
import duke.view.Gui;
import javafx.application.Application;

public class Duke {
    private TaskList tasks;
    private Parser in;
    private final UiController uiController;
    private final ListController listController;

    /**
     * Initiate Duke with default Parser, TaskList and Controllers for it to function
     */
    public Duke() {
        in = new Parser();
        tasks = new TaskList();
        uiController = new UiController(this, tasks);
        listController = new ListController(tasks);
    }

    public UiController getUiController() {
        return uiController;
    }

    public ListController getListController() {
        return listController;
    }

    /**
     * Initiate the GUI interface
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
