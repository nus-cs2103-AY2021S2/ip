package duke;

import duke.controller.ListController;
import duke.controller.Parser;
import duke.controller.UIController;
import duke.view.Gui;
import duke.model.task.TaskList;
import javafx.application.Application;

public class Duke {
    private TaskList tasks;
    private Parser in;
    private final UIController uiController;
    private final ListController listController;

    /**
     * Initiate Duke with default Parser and TaskList for it to function
     */
    public Duke() {
        in = new Parser();
        tasks = new TaskList();
        uiController = new UIController(this, tasks);
        listController = new ListController(tasks);
    }

    public UIController getUiController() {
        return uiController;
    }

    public ListController getListController() {
        return listController;
    }

    public TaskList getTasks() {
        return tasks;
    }


    /**
     * Initiate the GUI interface
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
