package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

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
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        try {
            manager.deleteTask(this.index);
            storage.writeToDisk(manager.getStore());
            return "Task deleted";
        } catch (IndexOutOfBoundsException err) {
            return "oops, number must be between 1 and number of tasks (inclusive).";
        } catch (Exception err) {
            return ":( sorry i don't recognise this format. type help for more info!";
        }

    }
}
