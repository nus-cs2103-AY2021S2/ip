package duke.command;


import duke.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class containing data and methods specific to a Delete command.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String[] command) {
        super.command = command;
    }

    /**
     * Obtains task to be deleted from the arguments from command initialisation and deletes it from the data file.
     *
     * @throws DukeException if argument passed does not correspond to a valid task number.
     */
    @Override
    public void process() throws DukeException {
        try {
            int id = Integer.parseInt(command[1]) - 1;
            Task task = TaskList.deleteTask(id, Storage.getTasks());
            Ui.displayDeletedTask(task, Storage.getTasks());
            Storage.updateDataFile();
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
