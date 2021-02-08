package duke.command;

import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.exception.TaskIndexOutOfBoundException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a DoneCommand.
 */
public class DoneCommand extends IndexCommand {
    /**
     * Constructs a DoneCommand.
     * @param index The full command from user's input.
     */
    public DoneCommand(int index) {
        super(index);
    }

    /**
     * Marks the specified task in the task list as completed.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @throws DukeException If error occurs during the process.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DescriptionMissingException, TaskIndexOutOfBoundException {
        if (index < tasks.size()) {
            Task completedTask = tasks.get(index);
            completedTask.complete();
            storage.updateInFile(tasks);
            ui.printTaskCompleted(completedTask);
            return ui.completeTaskResponse(completedTask);
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }
}
