package duke.command;

import java.util.ArrayList;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

/**
 * ShowListCommand, which shows the tasks on main.java.duke Task List when executed
 */

public class ShowListCommand implements Command {

    /**
     * Executes ShowListCommand, which shows the tasks on main.java.duke Task List
     *
     * @param dukeTaskList give dukeTaskList
     * @param dukeUi give dukeUi
     * @param dukeStorage give dukeStorage
     */
    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        dukeUi.showTaskList(currentTaskList);
    }

    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        return dukeUi.returnTaskList(currentTaskList);
    }
}
