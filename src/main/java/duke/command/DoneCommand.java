package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.util.MassOps;

/**
 * Class containing data and methods specific to a Done command.
 */
public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super.command = command;
    }

    /**
     * Obtains tasks to be marked as done from the arguments from initialisation and marks it as done.
     *
     * @throws DukeException if argument passed does not correspond to a valid task number or range.
     */
    @Override
    public void process() throws DukeException {
        try {
            int[] range = MassOps.identifyRange(command[1]);
            ArrayList<Task> tasks = MassOps.getTasksWithinRange(range);
            for (Task t : tasks) {
                t.markDone();
            }
            Ui.displayDone(tasks);
            Storage.updateDataFile();
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        } catch (Exception e) {
            throw new DukeException("Problem in DoneCommand");
        }
    }
}
