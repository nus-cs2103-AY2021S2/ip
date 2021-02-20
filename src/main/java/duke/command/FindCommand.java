package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/** Find command is used when user wants to find all tasks that contain a certain keyword. */
public class FindCommand extends Command {
    private String keyword;

    /** Initialises find command with keyword. */
    public FindCommand(String description) {
        super("These seem to match what you're looking for: ");
        this.keyword = description.replaceAll("find ", "");
    }

    /** Executes find command to present all tasks containing keyword.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        String found = manager.find(this.keyword);
        if (found.equals("")) {
            return String.format("You have no tasks that matches \"%s\"", this.keyword);
        }
        return this.message + "\n" + manager.find(this.keyword);
    }
}
