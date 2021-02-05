package duke;

/** List command is used when user wants to view all tasks he/she has. */
public class ListCommand extends Command {

    /** Initialises list command. */
    public ListCommand() {
        super("");
    }

    /** Executes list command to display all user's tasks.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        manager.displayTasks();
    }

}
