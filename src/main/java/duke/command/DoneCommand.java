package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;
import duke.exception.DukeStorageException;
import duke.task.Task;

/**
 * Class representing a Done Command.
 */
public class DoneCommand extends Command {
    private int completedTaskIdx;

    /**
     * Constructor for DoneCommand.
     *
     * @param completedTaskIdx The index of the completed task.
     */
    public DoneCommand(int completedTaskIdx) {
        this.completedTaskIdx = completedTaskIdx;
    }

    /**
     * Executes the done command.
     * Marks the task as done.
     * Updates the task storage.
     * Prompts the user that the task has been marked done.
     *
     * @param tasks List of tasks.
     * @param ui Formats and prints message to user.
     * @param storage Updates tasks.txt of the completed task.
     * @return true.
     */
    public String execute(TaskList tasks, Ui ui, TaskStorage storage) {
        try {
            Task completedTask = tasks.getTask(completedTaskIdx);
            completedTask.setDone();
            storage.storeData(tasks);
            return ui.formatDoneCmdMsg(completedTask);
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }
}
