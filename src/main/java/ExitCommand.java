package duke;

/** Exit command is used when user bids chatbot goodbye. */
public class ExitCommand extends Command {

    /** Initialises exit command. */
    public ExitCommand() {
        super("");
    }

    /**
     * Executes exit command to bid user goodbye and exit chat.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        ui.sayBye();
    }
}
