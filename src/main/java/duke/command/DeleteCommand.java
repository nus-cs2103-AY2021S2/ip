package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command to delete a task
 */
public class DeleteCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Constructor method
     * @param input The input command from the user.
     */
    public DeleteCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    /**
     * Executes a delete command.
     * Deletes a task from the list of tasks.
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object
     * @param storage Standard storage object
     * @throws DukeException if delete command is missing a number/description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        if(strArr.length == 1) {
            throw new DukeException("☹ OOPS!!! I don't know which task to delete.");
        }
        int idx = Integer.parseInt(strArr[1]) - 1;
        Task t = tasks.removeTask(idx);
        storage.removeDataInFile(idx + 1, tasks.getSize());
        ui.printTaskRemoved(t);
        ui.printNoOfItems(tasks);

    }
}
