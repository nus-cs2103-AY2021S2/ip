package duke.command;


import java.util.ArrayList;

import duke.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.MassOps;

/**
 * Class containing data and methods specific to a Delete command.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String[] command) {
        super.command = command;
    }

    /**
     * Obtains tasks to be deleted from the arguments from command initialisation and deletes it from the data file.
     *
     * @throws DukeException if argument passed does not correspond to a valid task number or range.
     */
    @Override
    public void process() throws DukeException {
        try {
            int[] range = MassOps.identifyRange(command[1]);
            ArrayList<Task> tasks = MassOps.getTasksWithinRange(range);
            for (Task t : tasks) {
                TaskList.deleteTask(t, Storage.getTasks());
            }
            Ui.displayDeletedTask(tasks, Storage.getTasks());
            Storage.updateDataFile();
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
