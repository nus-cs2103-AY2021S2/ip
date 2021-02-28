package ronald.command;


import ronald.RonaldException;
import ronald.ui.Ui;
import ronald.storage.Storage;
import ronald.task.Deadline;
import ronald.task.Task;
import ronald.task.TaskList;

/**
 * Class containing data and methods specific to a Deadline command.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String[] command) {
        super.command = command;
    }

    /**
     * Creates Deadline object from commands provided during initialisation of the DeadlineCommand object. Adds it to
     * the data file and prints the added Deadline object.
     *
     * @throws RonaldException if arguments provided to the DeadlineCommand object are invalid.
     */
    @Override
    public void process() throws RonaldException {
        Task task = Deadline.createDeadline(command);
        TaskList.addTask(task, Storage.getTasks());
        Storage.updateDataFile();
        Ui.displayAddedTask(task, Storage.getTasks());
    }
}
