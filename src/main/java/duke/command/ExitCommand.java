package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

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
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        return ui.sayBye();
    }
}
