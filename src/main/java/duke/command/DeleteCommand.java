package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskIndexOutOfBoundException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a DeleteCommand.
 */
public class DeleteCommand extends IndexCommand {
    /**
     * Constructs a DeleteCommand.
     * @param fullCommand Full command from the user's input.
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Deletes the specified task from the task list.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @throws DukeException If error occurs during the process.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = getIndex();
        if (index < tasks.size()) {
            Task removingTask = tasks.get(index);
            tasks.remove(index);
            storage.updateInFile(tasks);
            System.out.println(" Following task is removed:");
            System.out.println("  " + removingTask);
            System.out.println(" Now you have " + tasks.size() + " tasks.");
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }

    }
}
