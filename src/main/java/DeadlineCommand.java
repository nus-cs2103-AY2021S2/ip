package duke;

/**
 * Deadline command is used for user to add a deadline task.
 */
public class DeadlineCommand extends AddCommand {

    /** Initialises a new deadline command with the task information. */
    public DeadlineCommand(String description) {
        super(description.replaceAll("deadline ", ""));
    }

    /** 
     * Executes deadline command to add deadline task and respond to user.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        Task t = new Deadline(this.description.split(" by ")[0], this.description.split(" by ")[1]);;
        manager.addTask(t);
        this.message += t.toString() + "\n" 
            + String.format("Now you have %s tasks in the list.", manager.taskVolume());
        storage.writeToDisk(manager.getStore());
        ui.replyWith(this.message);
    }
}
