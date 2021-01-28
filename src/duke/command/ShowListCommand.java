package duke.command;

import duke.maincomponents.Storage;
import duke.task.Task;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;

/**
 * ShowListCommand, which shows the tasks on duke Task List when executed
 */
import java.util.ArrayList;

public class ShowListCommand implements Command {
    /**
     * Constructor for ShowListCommand
     */
    public ShowListCommand(){
    }

    /**
     * Executes ShowListCommand, which shows the tasks on duke Task List
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
