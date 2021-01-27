package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;

/**
 * Class representing a Done Command.
 */
public class DoneCommand extends Command {
    private int completedTaskIdx;

    /**
     * Constructor for DoneCommand.
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
     * @param tasks List of tasks.
     * @param ui Formats and prints message to user.
     * @param storage Updates tasks.txt of the completed task.
     */
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        Task completedTask = tasks.getTask(completedTaskIdx);
        completedTask.markDone();
        storage.storeData(tasks);
        ui.print("Nice! I have marked this task as done:\n\t\t " + completedTask);
    }
}
