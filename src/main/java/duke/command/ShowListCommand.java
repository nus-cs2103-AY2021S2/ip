package main.java.duke.command;

import java.util.ArrayList;

import main.java.duke.maincomponents.Storage;
import main.java.duke.maincomponents.TaskList;
import main.java.duke.maincomponents.Ui;
import main.java.duke.task.Task;



/**
 * ShowListCommand, which shows the tasks on main.java.duke Task List when executed
 */
public class ShowListCommand implements Command {
    /**
     * Constructor for ShowListCommand
     */
    public ShowListCommand() {
    }

    /**
     * Executes ShowListCommand, which shows the tasks on main.java.duke Task List
     * @param dukeTaskList give dukeTaskList
     * @param dukeUi give dukeUi
     * @param dukeStorage give dukeStorage
     */
    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        Ui.showReturnTaskList(currentTaskList);
    }
}
