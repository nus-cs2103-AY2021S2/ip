package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/** Encapsulates information of a possible command from user. */
public abstract class Command {
    protected String message;

    /** Initialises each command with a standard message. */
    public Command(String msg) {
        this.message = msg;
    }

    /**
     * Execute each command to achieve respective effect.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public abstract String execute(TaskManager manager, Ui ui, Storage storage);
}
