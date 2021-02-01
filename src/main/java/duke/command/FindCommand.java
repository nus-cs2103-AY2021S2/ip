package duke.command;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand implements Command{
    String stringToFind;

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        ArrayList<Task> newTaskList = new ArrayList<>();

        for (int i = 0; i < currentTaskList.size(); i++) {

            Task currentTask = currentTaskList.get(i);
            if (currentTask.descriptionContains(stringToFind)){
                newTaskList.add(currentTask);
            }
        }

        dukeUi.showFoundTaskList(newTaskList);
    }

    public FindCommand(String s){
        stringToFind = s;
    }
}
