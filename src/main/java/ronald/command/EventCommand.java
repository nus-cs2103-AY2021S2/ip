package ronald.command;


import ronald.RonaldException;
import ronald.ui.Ui;
import ronald.storage.Storage;
import ronald.task.Event;
import ronald.task.Task;
import ronald.task.TaskList;

/**
 * Class containing data and methods specific to a Event command.
 */
public class EventCommand extends Command {

    public EventCommand(String[] command) {
        super.command = command;
    }

    /**
     * Creates Event object from commands provided during initialisation of the EventCommand object. Adds it to the
     * data file and prints the added Event object.
     *
     * @throws RonaldException if arguments provided to the EventCommand object are invalid.
     */
    @Override
    public void process() throws RonaldException {
        Task task = Event.createEvent(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
