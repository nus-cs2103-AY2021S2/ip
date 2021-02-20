package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.ui.Ui;

/** Todo command is used when user wants to add a new task without date information. */
public class TodoCommand extends AddCommand {

    /** Initialises a todo command with its description. */
    public TodoCommand(String description) {
        super(description);
    }

    /** Executes todo command to add task to list.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        Task t = new Todo(this.description.split("todo ")[1]);
        manager.addTask(t);
        this.message += t.toString() + "\n"
            + String.format("Now you have %s tasks in the list.", manager.taskVolume());
        storage.writeToDisk(manager.getStore());
        return this.message;
    }
}
