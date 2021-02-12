package duke.command;


import duke.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

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
     * @throws DukeException if arguments provided to the EventCommand object are invalid.
     */
    @Override
    public void process() throws DukeException {
        Task task = Event.createEvent(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
