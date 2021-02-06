package duke.command;

import java.util.ArrayList;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

public class FindCommand implements Command {
    private final String stringToFind;

    public FindCommand(String s) {
        stringToFind = s;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        ArrayList<Task> newTaskList = new ArrayList<>();

        for (Task currentTask : currentTaskList) {
            if (currentTask.descriptionContains(stringToFind)) {
                newTaskList.add(currentTask);
            }
        }
        dukeUi.showFoundTaskList(newTaskList);
    }

    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        ArrayList<Task> newTaskList = new ArrayList<>();

        for (Task currentTask : currentTaskList) {
            if (currentTask.descriptionContains(stringToFind)) {
                newTaskList.add(currentTask);
            }
        }
        return dukeUi.returnFoundTaskList(newTaskList);
    }
}
