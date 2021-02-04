package duke.command;

import duke.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Class containing data and methods specific to a Done command.
 */
public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super.command = command;
    }

    /**
     * Obtains task to be marked as done from the arguments from initialisation and marks it as done.
     *
     * @throws DukeException if argument passed does not correspond to a valid task number.
     */
    @Override
    public void process() throws DukeException {
        try {
            Task task = Storage.getTasks().get(Integer.parseInt(command[1]) - 1);
            task.markDone();
            Ui.displayDone(task);
            Storage.updateDataFile();
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
