package duke.command;

import duke.maincomponents.Storage;
import duke.task.Task;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;

import java.util.ArrayList;

public class EventCommand implements Command {
    private ArrayList<String> eventDescription;
    public EventCommand(ArrayList<String> a) {
        eventDescription = a;
    }


    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task eventTask = dukeTaskList.addEventTask(eventDescription);
        dukeUi.showAddedTask(eventTask, dukeTaskList.getNumberOfTasks());
    }


}
