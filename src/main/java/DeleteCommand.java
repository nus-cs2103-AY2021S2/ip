package duke;

/**
 * Delete command is used when user wants to remove a specific task.
 */
public class DeleteCommand extends Command {
    protected int index;

    /** Initialises a new delete command with target task number. */
    public DeleteCommand(String description) {
        super("");
        this.index = Integer.valueOf(description.split(" ")[1]);
    }

    /**
     * Executes delete command to remove corresponding task from list.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        manager.deleteTask(this.index);
        ui.replyWith("Task deleted.");
        storage.writeToDisk(manager.getStore());
    }
}
