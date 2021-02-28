package ronald.command;

import java.util.ArrayList;

import ronald.RonaldException;
import ronald.ui.Ui;
import ronald.storage.Storage;
import ronald.task.Task;
import ronald.util.MassOps;

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
     * @throws RonaldException if argument passed does not correspond to a valid task number or range.
     */
    @Override
    public void process() throws RonaldException {
        try {
            int[] range = MassOps.identifyRange(command[1]);
            ArrayList<Task> tasks = MassOps.getTasksWithinRange(range);
            for (Task t : tasks) {
                t.markDone();
            }
            Ui.displayDone(tasks);
            Storage.updateDataFile();
        } catch (RonaldException e) {
            throw new RonaldException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new RonaldException("That doesn't seem like a valid order number...");
        } catch (Exception e) {
            throw new RonaldException("Problem in DoneCommand");
        }
    }
}
