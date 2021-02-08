package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for showing all the tasks in the list of tasks.
 */
public class ListCommand extends Command {
    public static final boolean IS_EXIT = false;

    /**
     * Constructor method
     */
    public ListCommand() {
        super(IS_EXIT);
    }

    /**
     * Executes method for list command.
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object
     * @param storage Standard storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("    Here are the tasks in your list:");
        for(int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            int j = i + 1;
            ui.print("    " + (j) + "." + task.toString());
        }
    }
}
