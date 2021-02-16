package duke.command;

import java.util.ArrayList;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

/**
 * EventCommand, which adds a deadline task to Duke's TaskList when executed
 */

public class EventCommand implements Command {
    private final ArrayList<String> eventDescription;

    /**
     * Default constructor for Event Comamnd
     *
     * @param a arrayList of descriptions for the event task
     */
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
