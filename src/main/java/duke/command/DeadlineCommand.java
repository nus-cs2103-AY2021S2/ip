package duke.command;


import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class containing data and methods specific to a Deadline command
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String[] command) {
        super.command = command;
    }

    /**
     * Creates Deadline object from commands provided during initialisation of the DeadlineCommand object, adds it to
     * the data file and prints the added Deadline object.
     *
     * @throws DukeException if arguments provided to the DeadlineCommand object are invalid
     */
    @Override
    public void process() throws DukeException {
        Task task = Deadline.createDeadline(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
