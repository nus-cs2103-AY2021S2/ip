package duke;

/** Done command is used when user wants to mark a task as done. */
public class DoneCommand extends Command {
    protected int index;

    /**
     * Initialises a new done command with index of target task.
     */
    public DoneCommand(String description) {
        super("Wahoo you completed one task!");
        this.index = Integer.valueOf(description.split(" ")[1]);
    }

    /**
     * Executes done command to mark target task as done.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        manager.markTaskDone(this.index);
        ui.replyWith(this.message);
        storage.writeToDisk(manager.getStore());
    }

}
