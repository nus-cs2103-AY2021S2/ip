package duke.command;

import java.util.ArrayList;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

public class EventCommand implements Command {
    private final ArrayList<String> eventDescription;
    public EventCommand(ArrayList<String> a) {
        eventDescription = a;
    }


    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task eventTask = dukeTaskList.addEventTask(eventDescription);
        dukeUi.showAddedTask(eventTask, dukeTaskList.getNumberOfTasks());
    }

    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task eventTask = dukeTaskList.addEventTask(eventDescription);
        return dukeUi.returnAddedTask(eventTask, dukeTaskList.getNumberOfTasks());
    }
}
