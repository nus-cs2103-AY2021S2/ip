package duke.command;

import java.util.ArrayList;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;



public class ShowListCommand implements Command {

    public ShowListCommand(){
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        Ui.showReturnTaskList(currentTaskList);
    }
}
