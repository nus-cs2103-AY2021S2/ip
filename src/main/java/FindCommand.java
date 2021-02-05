package duke;

/** Find command is used when user wants to find all tasks that contain a certain keyword. */
public class FindCommand extends Command {
    String keyword;

    /** Initialises find command with keyword. */
    public FindCommand(String description) {
        super("Here are the matching tasks in your list: ");
        this.keyword = description.replaceAll("find ", "");
    }

    /** Executes find command to present all tasks containing keyword.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        ui.replyWith(this.message);
        manager.find(this.keyword);
    }
}
